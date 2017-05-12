package com.quickapp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

 
public class AdminController extends HttpServlet {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://172.30.65.44:5432/walletintqc";

	// Database credentials
	static final String USER = "wallet";
	static final String PASS = "wallet$01";
	public AdminController() {
		super();
	}
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException
	{
		String details = request.getParameter("details");
		RequestDispatcher rd = null;
		
			Connection conn = null;
			PreparedStatement stmt = null;
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      System.out.println("Connected database successfully...");
			     
			      
			      Statement st=conn.createStatement();
			      String sql="select * from location";
			      ResultSet rs = st.executeQuery(sql);
			      //STEP 5: Extract data from result set
			      List<Location> locations=new ArrayList<Location>();
			      while(rs.next()){
			        Location location=new Location();
			        location.setId(rs.getInt(1));
			        location.setDistrict(rs.getString(2));
			        location.setModal(rs.getString(3));
			        location.setVillage(rs.getString(4));
			        locations.add(location);
			      }
			      String json = new Gson().toJson(locations);
			     request.setAttribute("locations", json);
			      System.out.println(locations+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			      rs.close();
			      
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
		if(details != null && details.equals("location")){		   
			rd = request.getRequestDispatcher("/locationdetails.jsp");
		}else if(details != null && details.equals("ajax")){
			rd = request.getRequestDispatcher("/locationdetailsAjax.jsp");
		}else {
			rd = request.getRequestDispatcher("/adminhome.jsp");
		}
		
		
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		String ipAdress = request.getRemoteAddr();
		System.out.println("ipAdress>>>>>>>>>>>>>>>>>>>>"+ipAdress);
		String district=request.getParameter("district");
		String mname=request.getParameter("mname");
		String vname=request.getParameter("vname");
		System.out.println(district+"========="+vname+"========="+mname);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      String sql="INSERT INTO location"
		    			+ "(district_name, mandal_name,village_name,created_date) VALUES"
		    			+ "(?,?,?,?)";
		      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    
		      stmt = conn.prepareStatement(sql);
		      stmt.setString(1, district);
		      stmt.setString(2, mname);
		      stmt.setString(3, vname);
		      stmt.setString(4, sdf.format(new Date()));
		      int rows = stmt.executeUpdate();
		      
		      /* Statement st=conn.createStatement();
		      sql="select count(*) from hitcounter";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      int totalHit=0;
		      while(rs.next()){
		         //Retrieve by column name
		    	  totalHit  = rs.getInt(1);
		      }*/
		     /* request.setAttribute("totalHit", totalHit);
		      System.out.println(totalHit+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      rs.close();*/
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		
		
		
		
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/success.jsp");
		rd.forward(request, response);
		
	}
 
}