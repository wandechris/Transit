package com.wande.transit;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * This class defines the methods for basic operations of create, update & retrieve
 * for bus entity
 * 
 * @author 
 *
 */
public class Transit {

	/**
	 * Checks if the entity is existing and if it is not, it creates the entity
	 * else it updates the entity
	 * 
	 * @param VEHICLE
	 *          : id for the bus
	 * @param ADHERENCE
	 *          
	 * @param BLOCKID
	 *         
	 * @param BLOCK_ABBR
	 *         
	 * @param DIRECTION
	 *         
	 * @param LATITUDE
	 *         
	 * @param LONGITUDE
	 *         
	 * @param MSGTIME
	 *         
	 * @param ROUTE
	 *         
	 * @param STOPID
	 *         
	 * @param TIMEPOINT
	 *         
	 * @param TRIPID
	 *         
	 */
  public static void createOrUpdateCustomer(Bus bus) {
    Entity bus2 = getSingleCustomer(bus.getVEHICLE());
    if (bus2 == null) {
    	bus2 = new Entity("bus", bus.getVEHICLE());
    	bus2.setProperty("name", bus.getVEHICLE());
    	bus2.setProperty("adherence", bus.getADHERENCE());
    	bus2.setProperty("blockId", bus.getBLOCKID());
    	bus2.setProperty("blockAbbr", bus.getBLOCK_ABBR());
    	bus2.setProperty("direction", bus.getDIRECTION());
    	bus2.setProperty("latitude", bus.getLATITUDE());
    	bus2.setProperty("longitude", bus.getLONGITUDE());
    	bus2.setProperty("msgTime", bus.getMSGTIME());
    	bus2.setProperty("route", bus.getROUTE());
    	bus2.setProperty("stopId", bus.getSTOPID());
    	bus2.setProperty("timepoint", bus.getTIMEPOINT());
    	bus2.setProperty("tripId", bus.getTRIPID());
    	bus2.setProperty("date", bus.getDATE());
    	bus2.setProperty("hour", bus.getHOUR());
    } else {
      if (bus.getVEHICLE() != null && !"".equals(bus.getVEHICLE())) {
        bus2.setProperty("name", bus.getVEHICLE());
      }
      if (bus.getADHERENCE() != null && !"".equals(bus.getADHERENCE())) {
    	  bus2.setProperty("adherence", bus.getADHERENCE());
      }
      if (bus.getBLOCKID() != null && !"".equals(bus.getBLOCKID())) {
    	  bus2.setProperty("blockId", bus.getBLOCKID());
      }
      if (bus.getBLOCK_ABBR() != null && !"".equals(bus.getBLOCK_ABBR())) {
    	  bus2.setProperty("blockAbbr", bus.getBLOCK_ABBR());
      }
      if ( bus.getDIRECTION() != null && !"".equals( bus.getDIRECTION())) {
    	  bus2.setProperty("direction", bus.getDIRECTION());
      }
      if (bus.getLATITUDE() != null && !"".equals(bus.getLATITUDE())) {
    	  bus2.setProperty("latitude", bus.getLATITUDE());
      }
      if (bus.getLONGITUDE() != null && !"".equals(bus.getLONGITUDE())) {
    	  bus2.setProperty("longitude", bus.getLONGITUDE());
      }
      if (bus.getMSGTIME() != null && !"".equals(bus.getMSGTIME())) {
    	  bus2.setProperty("msgTime", bus.getMSGTIME());
      }
      if (bus.getROUTE() != null && !"".equals(bus.getROUTE())) {
    	  bus2.setProperty("route", bus.getROUTE());
      }
      if (bus.getSTOPID() != null && !"".equals(bus.getSTOPID())) {
    	  bus2.setProperty("stopId", bus.getSTOPID());
      }
      if (bus.getTIMEPOINT() != null && !"".equals(bus.getTIMEPOINT())) {
    	  bus2.setProperty("timepoint", bus.getTIMEPOINT());
      }
      if (bus.getTRIPID() != null && !"".equals(bus.getTRIPID())) {
    	  bus2.setProperty("tripId", bus.getTRIPID());
      }
    }
    Util.persistEntity(bus2);
  }

	/**
	 * List all the bus available
	 * 
	 * @return an iterable list with all the buses
	 */
  public static Iterable<Entity> getAllBuses() {
    Iterable<Entity> entities = Util.listEntities("bus", null, null);
    return entities;
  }

	/**
	 * Searches for a bus and returns the entity as an iterable The search is
	 * performed by creating a query and searching for the attribute
	 * 
	 * @param busName
	 * @return iterable with the buses searched for
	 */
  public static Iterable<Entity> getBus(String busName) {
    Iterable<Entity> entities = Util.listEntities("bus", "name",	busName);
    return entities;
  }

	/**
	 * Searches for a bus and returns the entity as an iterable The search is
	 * key based instead of query
	 * 
	 * @param busName
	 *          
	 * @return the entity with the username as key
	 */
  public static Entity getSingleCustomer(String busName) {
    Key key = KeyFactory.createKey("bus", busName);
    return Util.findEntity(key);
  }
}
