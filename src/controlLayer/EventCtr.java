package controlLayer;

import dbLayer.*;
import modelLayer.Event;

public class EventCtr {

	public int insertEvent(String name, String description) throws Exception{
		Event ev = new Event();
		ev.setName(name);
		ev.setDescription(description);
		int res = -1;
		
		try{
	          DbConnection.startTransaction();
	          DbEvent dbEv = new DbEvent();
		      res = dbEv.insertEvent(ev);
	          DbConnection.commitTransaction();
	         }
	         catch(Exception e)
	         {
	             DbConnection.rollbackTransaction();
	             throw new Exception("Event not inserted");
	         }
		
		return res;
	}
	
	public Event findEvent(String name) {
		DbEvent dbEv = new DbEvent();
		Event ev = dbEv.findEvent(name);
		
		return ev;
	}
	
	public int updateEvent(String name, String description, int eid) {
		DbEvent dbEv = new DbEvent();
		Event ev = new Event();
		int res = -1;
		
		ev.setName(name);
		ev.setDescription(description);
		ev.setEid(eid);
		
		res = dbEv.updateEvent(ev);
		
		return res;
	}
	
	public int removeEvent(int eid) {
		DbEvent dbEv = new DbEvent();
		Event ev = new Event();
		ev.setEid(eid);
		int res = -1;
		
		res = dbEv.removeEvent(ev);
		
		return res;
	}
}
