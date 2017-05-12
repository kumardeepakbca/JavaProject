<!DOCTYPE html>

<html lang="en">
<head>
    <!-- The jQuery library is a prerequisite for all jqSuite products -->
    <script type="text/ecmascript" src="js/jquery.min.js"></script> 
    <!-- This is the Javascript file of jqGrid -->   
    <script type="text/ecmascript" src="js/jquery.jqGrid.min.js"></script>
    <!-- This is the localization file of the grid controlling messages, labels, etc.-->
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="js/grid.locale-en.js"></script>
    <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <link rel="stylesheet" type="text/css" media="screen" href="css/jquery-ui.css" />
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
    <meta charset="utf-8" />
    <title>jqGrid Loading Data - JSON Live</title>
</head>
<body>

    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>

    <script type="text/javascript"> 
    
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                colModel: [
                    {
						label: 'Id',
                        name: 'id',
                        width: 120
                    },
                    {
						label: 'District',
                        name: 'district',
                        width: 120
                    },
                    {
						label: 'Mandal Name',
                        name: 'mandal',
                        width: 120
                    },
                    {
						label: 'Village',
                        name: 'village',
                        width: 120
                    }
                ],

                viewrecords: true, // show the current page, data rang and total records on the toolbar
                width: 780,
                height: 200,
                rowNum: 20,
				datatype: 'local',
                pager: "#jqGridPager",
				caption: "Load live data from stackoverflow"
            });

            fetchGridData();

            function fetchGridData() {
                
                var gridArrayData = [];
				// show loading message
				$("#jqGrid")[0].grid.beginReq();
                $.ajax({
                    url: "http://localhost:8080/quickApp/admin?details=ajax",
                    contentType: "json",
                    success: function (result) {
                    	var locations = JSON.stringify(eval("(" + result + ")"));
                    	var locObj=JSON.parse(locations);
                        for (var i = 0; i < locObj.length; i++) {
                            var item = locObj[i];
                            gridArrayData.push({
                                id: item.id,
                                district: item.district,
                                mandal: item.modal,
                                village: item.village
                            });                            
                        }
						// set the new data
						$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData});
						// hide the show message
						$("#jqGrid")[0].grid.endReq();
						// refresh the grid
						$("#jqGrid").trigger('reloadGrid');
                    }
                });
            }
    

        });

    </script>

    
</body>
</html>