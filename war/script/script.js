var image = {
				url : 'images/bus.png',
				// This marker is 20 pixels wide by 32 pixels tall.
				//size : new google.maps.Size(20, 32),
				// The origin for this image is 0,0.
				origin : new google.maps.Point(0, 0),
				// The anchor for this image is the base of the flagpole at 0,32.
				anchor : new google.maps.Point(0, 32)
			};
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 12,
				center : new google.maps.LatLng(33.7528383, -84.3898637),
				mapTypeId : google.maps.MapTypeId.ROADMAP
			});

			var infowindow = new google.maps.InfoWindow();
			setInterval(function() {
				setTimeout(function() {
					loadData();
					
				}, 500);
			}, 1000);
			
			
			
			
			var marker, i;
			var url = "/transit"
			var name;
			var lat;
			var lon;
			var direction;
			var adhere;
			var currentLoc;
			var upDate;
			
			var marker;
			var myLatLng;
			var oldMarkers = [];
			var totalVehicles = [];
			var vehicles = [];
			var nvehicles = [];
			var totalVehiclesN = [];
			var totalVehiclesS = [];
			var svehicles = [];
			var morning = [];
			var afternoon = [];
			var evening = [];
			var night = [];
			var currentDate = new Date();
			
			
			var date2 = currentDate.getDate();
		    var month2 = currentDate.getMonth() + 1;
		    var year2 = currentDate.getFullYear();
		    var today2 = date2+"/"+month2+"/"+year2;
		    
		    
		    var morningSouthDir =0 ;
		    var morningNorthDir = 0;
		    
		    var afternoonSouthDir =0;
		    var afternoonNorthDir =0 ;
		    
		    var eveningSouthDir =0;
		    var eveningNorthDir =0;
		    
		    var nightSouthDir =0;
		    var nightNorthDir =0;
		    
		    
		    
		    
		    function inArray (value, array) {
		      return array.indexOf(value) > -1 ? true : false;
			}
			
				
			function loadData() {
				jQuery.support.cors = true;
				$.getJSON(url, function(response) {
					handleData(response);
				});
			}
			
			
		
					
			function adheree (value) {
				var x;
				if(value < 0){
					x = Math.abs(value);
					return x+"mins late";
				}else if(value == 0){
					return "on Time";
				}else
			      return value+"mins early";
			}
			


			function handleData(response) {
				var n;
					nvehicles = [];
					svehicles = [];
					vehicles = [];
					$("#details").html("");
				
				
				

					if(oldMarkers && oldMarkers.length !== 0) {
						for(var i = 0; i < oldMarkers.length; ++i) {
							oldMarkers[i].setMap(null); 
						}
						
					}//alert(name);
					var buses = response.data;
					//console.log(buses);
				for(var i = 0; i < buses.length; i++) {
					name = buses[i].name;
					direction = buses[i].direction;
					adhere = buses[i].adherence;
					lat = buses[i].latitude;
					lon = buses[i].longitude;
					upDate = buses[i].msgTime;
					currentLoc = buses[i].timePoint;
					myLatLng = new google.maps.LatLng(lat, lon);
					marker = new google.maps.Marker({
						position : myLatLng,
						icon : image,
						map : map,
						title : "Bus Number: "+ name,
						zIndex : 1
					});
					
					
					if (inArray(name, vehicles)) {
						
						}else{
							vehicles.push(name);
							if (direction == "Northbound"){
								nvehicles.push(name);
						    }else{
						    	svehicles.push(name);
						     }
							$("#details").append("<div class="+"col-md-4"+"><b>Bus Number: </b>"+name+"<br><b>Current Location: </b> "+currentLoc+"<br> <b>Late or Early: </b> "+adheree(adhere)+" <br> <b>Heading: </b> "+direction+"<br> <b>Last Update: </b> "+upDate+"<br><br></div>");
						}
					oldMarkers.push( marker );
				}
				$("#nvehicle").html("<b>Number of bus NorthBound : </b>" + nvehicles.length);
				$("#svehicle").html("<b>Number of bus SouthBound : </b>" + svehicles.length);
			   	$("#vehicle").html("<b>Number of bus on Map : </b> " + vehicles.length);
			}
				
										
					
					
					
						
			
			
			getData("/transit",function(response) {
				workData(response);
			},null);
			
			function workData(response) {
				
				var dataa;
				var cdate;
				var date;
			    var month;
			    var year;
			    var today;
			    var tomorrow;
			    var hour;
			    var nxthour;
				dataa = response.data;
				//console.log(data);
				for(var i = 0; i < dataa.length; i++) {
					name = dataa[i].name;
					//console.log(name);
					direction = dataa[i].direction;
					adhere = dataa[i].adherence;
					lat = dataa[i].latitude;
					lon = dataa[i].longitude;
					upDate = dataa[i].msgTime;
					currentLoc = dataa[i].timePoint;
					cdate = dataa[i].date;
					
					hour = dataa[i].hour;
					
					var ty = today2.toString();
					console.log("current"+ty);
				//	console.log("old"+cdate);
					if(cdate == ty){
						
					
					totalVehicles.push(name);	
					console.log(totalVehicles);
					if (direction == "Northbound"){
						
					totalVehiclesN.push(name);
					
					}else{
						
			        	totalVehiclesS.push(name);
				     }
					
				    
				    if (hour <= 6) {
						night.push(name);
						if (direction == "Northbound"){
							nightNorthDir = nightNorthDir + 1;
						}else{
							nightSouthDir = nightSouthDir + 1;
						}
					}
					if (hour > 6 && hour <= 12) {
						morning.push(name);
						if (direction == "Northbound"){
							morningNorthDir = morningNorthDir + 1;
						}else{
							morningSouthDir = morningSouthDir + 1;
						}
					}if (hour > 12 && hour <= 18) {
						afternoon.push(name);
						if (direction == "Northbound"){
							afternoonNorthDir = afternoonNorthDir + 1;
						}else{
							afternoonSouthDir = afternoonSouthDir + 1;
						}
					}
					if (hour > 18 && hour <= 24) {
						evening.push(name);
						if (direction == "Northbound"){
							eveningNorthDir = eveningNorthDir + 1;
						}else{
							eveningSouthDir = eveningSouthDir + 1;
						}
					}
					
					
					}
				}
				
			}
			
			
			google.load("visualization", "1", {packages:["corechart"]});
		      google.setOnLoadCallback(drawChart);
		      function drawChart() {
		        var data = google.visualization.arrayToDataTable([
		          ['Transit', 'Northbound','Southbound'],
			      ['Morning' , morningNorthDir,morningSouthDir], 
		          ['Afternoon',    afternoonNorthDir,  afternoonSouthDir],
		          ['Evening',   eveningNorthDir,   eveningSouthDir],
		          ['Night', nightNorthDir, nightSouthDir]
		        ]);
		        
		        var data2 = google.visualization.arrayToDataTable([
		                                        		          ['Transit', 'direction'],
		                                        			      ['Northbound',totalVehiclesN.length], 
		                                        		          ['Southbound',totalVehiclesS.length]
		                                        		        ]);
		        
		        var data3 = google.visualization.arrayToDataTable([
			                                        		          ['Transit', 'Number of cars'],
			                                        		          ['Morning',morningNorthDir+morningSouthDir], 
			                                        		          ['Afternoon',afternoonNorthDir + afternoonSouthDir],
			                                        		          ['Evening', eveningNorthDir + eveningSouthDir],
			                                        		          ['Night',nightNorthDir + nightSouthDir]
			                                        		        ]);

		        var options = {
		          title: 'Transit',
		          'isStacked': true,
		          vAxis: {title: 'Number of buses',  titleTextStyle: {color: 'red'}}
		        };
		        

		        var chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
		        chart.draw(data, options);
		        
		        var chart = new google.visualization.PieChart(document.getElementById('piechart2'));
		        chart.draw(data2, options);
		        
		        var chart = new google.visualization.PieChart(document.getElementById('piechart3'));
		        chart.draw(data3, options);
		      }