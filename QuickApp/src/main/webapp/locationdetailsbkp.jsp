<%@ page import="java.sql.*" %>
<%@ page import="com.quickapp.Location" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<HTML>
    <HEAD>
        <TITLE>Location details</TITLE>
    </HEAD>

    <BODY>
        <H1>Location details</H1>

    <%    
          List<Location> locs =  (List<Location>)request.getAttribute("locations");
          Iterator<Location> itr = locs.iterator();%>
    <table>
 <% while (itr.hasNext()) {
 Location loc = (Location)itr.next();
 %>
    <tr>
      <td><%out.println(loc.getId()); %></td>
	  <td><%out.println(loc.getDistrict()); %></td>
	  <td><%out.println(loc.getModal()); %></td>
	  <td><%out.println(loc.getVillage()); %></td>
     
    </tr>
  <% }%>
</table>
    </BODY>
</HTML>