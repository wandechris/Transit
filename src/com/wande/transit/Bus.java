/**
 * 
 */
package com.wande.transit;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author LRB
 * 
 */
@Entity(name = "Bus")
public class Bus {
	
	

	@Id
	private String VEHICLE;
	private String ADHERENCE;
	private String BLOCKID;
	private String BLOCK_ABBR;
	private String DIRECTION;
	private String LATITUDE;
	private String LONGITUDE;
	private String MSGTIME;
	private String ROUTE;
	private String STOPID;
	private String TIMEPOINT;
	private String TRIPID;
	private String DATE;
	private String HOUR;
	
	

	/**
	 * @return the vEHICLE
	 */
	public String getVEHICLE() {
		return VEHICLE;
	}

	/**
	 * @param vEHICLE
	 *            the vEHICLE to set
	 */
	public void setVEHICLE(String vEHICLE) {
		VEHICLE = vEHICLE;
	}

	/**
	 * @return the aDHERENCE
	 */
	public String getADHERENCE() {
		return ADHERENCE;
	}

	/**
	 * @param aDHERENCE
	 *            the aDHERENCE to set
	 */
	public void setADHERENCE(String aDHERENCE) {
		ADHERENCE = aDHERENCE;
	}

	/**
	 * @return the bLOCKID
	 */
	public String getBLOCKID() {
		return BLOCKID;
	}

	/**
	 * @param bLOCKID
	 *            the bLOCKID to set
	 */
	public void setBLOCKID(String bLOCKID) {
		BLOCKID = bLOCKID;
	}

	/**
	 * @return the bLOCK_ABBR
	 */
	public String getBLOCK_ABBR() {
		return BLOCK_ABBR;
	}

	/**
	 * @param bLOCK_ABBR
	 *            the bLOCK_ABBR to set
	 */
	public void setBLOCK_ABBR(String bLOCK_ABBR) {
		BLOCK_ABBR = bLOCK_ABBR;
	}

	/**
	 * @return the dIRECTION
	 */
	public String getDIRECTION() {
		return DIRECTION;
	}

	/**
	 * @param dIRECTION
	 *            the dIRECTION to set
	 */
	public void setDIRECTION(String dIRECTION) {
		DIRECTION = dIRECTION;
	}

	/**
	 * @return the lATITUDE
	 */
	public String getLATITUDE() {
		return LATITUDE;
	}

	/**
	 * @param lATITUDE
	 *            the lATITUDE to set
	 */
	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}

	/**
	 * @return the lONGITUDE
	 */
	public String getLONGITUDE() {
		return LONGITUDE;
	}

	/**
	 * @param lONGITUDE
	 *            the lONGITUDE to set
	 */
	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	/**
	 * @return the mSGTIME
	 */
	public String getMSGTIME() {
		return MSGTIME;
	}

	/**
	 * @param mSGTIME
	 *            the mSGTIME to set
	 */
	public void setMSGTIME(String mSGTIME) {
		MSGTIME = mSGTIME;
	}

	/**
	 * @return the rOUTE
	 */
	public String getROUTE() {
		return ROUTE;
	}

	/**
	 * @param rOUTE
	 *            the rOUTE to set
	 */
	public void setROUTE(String rOUTE) {
		ROUTE = rOUTE;
	}

	/**
	 * @return the sTOPID
	 */
	public String getSTOPID() {
		return STOPID;
	}

	/**
	 * @param sTOPID
	 *            the sTOPID to set
	 */
	public void setSTOPID(String sTOPID) {
		STOPID = sTOPID;
	}

	/**
	 * @return the tIMEPOINT
	 */
	public String getTIMEPOINT() {
		return TIMEPOINT;
	}

	/**
	 * @param tIMEPOINT
	 *            the tIMEPOINT to set
	 */
	public void setTIMEPOINT(String tIMEPOINT) {
		TIMEPOINT = tIMEPOINT;
	}

	/**
	 * @return the tRIPID
	 */
	public String getTRIPID() {
		return TRIPID;
	}

	/**
	 * @param tRIPID
	 *            the tRIPID to set
	 */
	public void setTRIPID(String tRIPID) {
		TRIPID = tRIPID;
	}

	public Bus create (String name, String adh, String blocki, String blocka, String dir, String lat, String lon,
			String msg, String route, String stop, String time, String trip, String date,String hour){
		Bus g = new Bus();
		g.setVEHICLE(name);
		g.setADHERENCE(adh);
		g.setBLOCK_ABBR(blocka);
		g.setBLOCKID(blocki);
		g.setDIRECTION(dir);
		g.setLATITUDE(lat);
		g.setLONGITUDE(lon);
		g.setMSGTIME(msg);
		g.setROUTE(route);
		g.setSTOPID(stop);
		g.setTIMEPOINT(time);
		g.setTRIPID(trip);
		g.setDATE(date);
		g.setHOUR(hour);
		
		return g;
		
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getHOUR() {
		return HOUR;
	}

	public void setHOUR(String hOUR) {
		HOUR = hOUR;
	}
	
}
