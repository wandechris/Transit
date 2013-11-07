/**
 * Copyright 2011 Google
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wande.transit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * This servlet responds to the request corresponding to users. The class
 * creates and manages the User Entity
 * 
 * @author
 */
@SuppressWarnings("serial")
public class TransitServlet extends BaseServlet {

  private static final Logger logger = Logger.getLogger(TransitServlet.class.getCanonicalName());

	/**
	 * Get the requested customer entities in JSON format
	 */
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

    super.doGet(req, resp);
    logger.log(Level.INFO, "Obtaining Bus listing");
    String searchFor = req.getParameter("q");
    PrintWriter out = resp.getWriter();
    Iterable<Entity> entities = null;
    if (searchFor == null || searchFor.equals("")) {
      entities = Transit.getAllBuses();
      out.println(Util.writeJSON(entities));
    } else {
      entities = Transit.getBus(searchFor);
      out.println(Util.writeJSON(entities));
    }
    return;
  }

	/**
	 * Insert the new customer
	 */
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    logger.log(Level.INFO, "Creating Bus");
    String name = req.getParameter("name");
    String adherence = req.getParameter("adherence");
    String blockId = req.getParameter("blockId");
    String blockAbbr = req.getParameter("blockAbbr");
    String direction = req.getParameter("direction");
    String latitude = req.getParameter("latitude");
    String longitude = req.getParameter("longitude");
    String msgTime = req.getParameter("msgTime");
    String route = req.getParameter("route");
    String stopId = req.getParameter("stopId");
    String timepoint = req.getParameter("timepoint");
    String tripId = req.getParameter("tripId");
    String date = req.getParameter("date");
    String hour = req.getParameter("hour");
    Bus bus = new Bus();
    Transit.createOrUpdateCustomer(bus.create(name, adherence, blockId, blockAbbr, direction, latitude, longitude, msgTime, route, stopId, timepoint, tripId, date,hour));
  }

	/**
	 * Delete the bus
	 */
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    String busName = req.getParameter("id");
    logger.log(Level.INFO, "Deleting User {0}", busName);
    Key key = KeyFactory.createKey("bus", busName);
    try {
        //CASCADE_ON_DELETE
      Util.deleteFromCache(key);
      Util.deleteEntity(key);
    } catch (Exception e) {
      String msg = Util.getErrorResponse(e);
      resp.getWriter().print(msg);
    }
  }

	/**
	 * Redirect the call to doDelete or doPut method
	 */
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    String action = req.getParameter("action");
    if (action.equalsIgnoreCase("delete")) {
      doDelete(req, resp);
      return;
    } else if (action.equalsIgnoreCase("put")) {
      doPut(req, resp);
      return;
    }
  }
}
