package com.wande.transit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("serial")
public class SaveServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		JsonElement name = null;
		JsonArray  arry = null;
		StringBuffer res = null;
		
		DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
	       //get current date time with Date()
	       Date date = new Date();
	       Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
			calendar.setTime(date); 

		PrintWriter out = resp.getWriter();
		try {
		    URL url = new URL("http://developer.itsmarta.com/BRDRestService/BRDRestService.svc/GetBusByRoute/110");
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestProperty("X-Custom-Header", "xxx");
		    connection.setRequestProperty("Content-Type", "application/json");

		    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
		        // OK
		        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		        res = new StringBuffer();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            res.append(line);
		        }
		        
		        JsonParser parser = new JsonParser();
		        Object obj = parser.parse(res.toString());
		        
		        
		        

		       arry = (JsonArray)obj;
		       
		      for(int i = 0; i < arry.size(); i++){
		        	JsonObject c = (JsonObject) arry.get(i);
		        	 name = c.get("VEHICLE");
		        	 JsonElement adherence = c.get("ADHERENCE");
			            JsonElement blockId = c.get("BLOCKID");
			            JsonElement blockAbbr = c.get("BLOCK_ABBR");
			            JsonElement direction = c.get("DIRECTION");
			            JsonElement latitude = c.get("LATITUDE");
			            JsonElement longitude = c.get("LONGITUDE");
			            JsonElement msgTime = c.get("MSGTIME");
			            JsonElement route = c.get("ROUTE");
			            JsonElement stopId = c.get("STOPID");
			            JsonElement timepoint = c.get("TIMEPOINT");
			            JsonElement tripId = c.get("TRIPID");
			            String date2 = dateFormat.format(date);
			            String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
			            Bus bus = new Bus();
			            Transit.createOrUpdateCustomer(bus.create(name.getAsString(), adherence.getAsString(), blockId.getAsString(), blockAbbr.getAsString(), direction.getAsString(), latitude.getAsString(), longitude.getAsString(), msgTime.getAsString(), route.getAsString(), stopId.getAsString(), timepoint.getAsString(), tripId.getAsString(), date2, hour));
		        	 
		      }
		        
		      reader.close();
		        //int count = jsonObj.getInt("count");
		        

		    } else {
		        // Server returned HTTP error code.
		    }

		} catch (Exception e) {
		}
		
		out.println("ok");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
