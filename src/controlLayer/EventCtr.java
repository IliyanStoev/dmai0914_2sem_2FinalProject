package controlLayer;

import java.sql.Date;
import java.util.ArrayList;

import modelLayer.Employee;
import modelLayer.Event;
import dbLayer.DbConnection;
import dbLayer.DbEvent;

public class EventCtr {

	public int insertEvent(String name, Employee projectManager, Date date, double budget, String description) throws Exception{
		Event ev = new Event();
		ev.setName(name);
		ev.setProjectManager(projectManager);
		ev.setDate(date);
		ev.setBudget(budget);
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
	
	public ArrayList<Event> findAllEvents() {
	      DbEvent dbEv = new DbEvent();
	      ArrayList<Event> allEv = new ArrayList<Event>();
	      allEv = dbEv.getAllEvents();
	      return allEv;
	}
	
	public int updateEvent(String name, double budget, String description, int eid) {
		DbEvent dbEv = new DbEvent();
		Event ev = new Event();
		int res = -1;
		
		ev.setName(name);
		ev.setBudget(budget);
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
	
	/*
	 * Employee - event association methods
	 */
	
	public int insertEmpEvent(Event ev, Employee emp) throws Exception{
		int res = -1;
		
		try{
	          DbConnection.startTransaction();
	          DbEvent dbEv = new DbEvent();
		      res = dbEv.insertEmpEvent(ev, emp);
	          DbConnection.commitTransaction();
	         }
	         catch(Exception e)
	         {
	             DbConnection.rollbackTransaction();
	             throw new Exception("Event not associated to the specified employee");
	         }
		
		return res;
	}
	
	public ArrayList<Event> findEmpEvents(Employee emp) {
		DbEvent dbEv = new DbEvent();
		ArrayList<Event> empEv = new ArrayList<Event>();
		empEv = dbEv.getEmpEvents(emp.getCpr());
		
		for(Event ev : empEv) {
			System.out.println(ev.getName());			
		}
		return empEv;
	}
	
	public boolean isDuplicate(Event ev, Employee emp) {
		DbEvent dbEv = new DbEvent();
		
		return dbEv.isDuplicate(ev, emp);
	}
}
