<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
  <head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '부서');
        data.addColumn('number', '인원수');
        
        var chartData = [];
        //ajax data 조회
        $.ajax({
        	url: "ajax/empStat.do",
        	dataType:"json" ,
        	async :false,
        	success: function(result){
        		for(i=0; i<result.length;i++){
        			chartData.push([result[i].department_id, result[i].cnt])
        		}
        	}
        	
        });
        data.addRows(chartData);
        
        //set chart option
        data.addRows([
          ['인사', 10],
          ['개발', 20],
          ['총부', 5],
       
        ]);

        // Set chart options
        var options = {'title':'부서별 인원수',
                       'width':800,
                       'height':400};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>
