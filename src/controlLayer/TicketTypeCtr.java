package controlLayer;

import java.util.ArrayList;

import modelLayer.Event;
import modelLayer.TicketType;
import dbLayer.DbConnection;
import dbLayer.DbEvent;
import dbLayer.DbTicketType;

public class TicketTypeCtr {

	public int insertTicketType(Event ev, String name, int inStock, double price, String description) throws Exception{
		TicketType tt = new TicketType();
		tt.setEv(ev);
		tt.setName(name);
		tt.setInStock(inStock);
		tt.setPrice(price);
		tt.setDescription(description);
		int res = -1;
		
		try{
	          DbConnection.startTransaction();
	          DbTicketType dbTt = new DbTicketType();
		      res = dbTt.insertTicketType(tt);
	          DbConnection.commitTransaction();
	         }
	         catch(Exception e)
	         {
	             DbConnection.rollbackTransaction();
	             throw new Exception("Ticket Type not inserted");
	         }
		
		return res;
	}
	
	public TicketType findTicketType(String name) {
		DbTicketType dbTt = new DbTicketType();
		TicketType tt = dbTt.findTicketType(name);
		
		return tt;
	}
	
	public ArrayList<TicketType> findAllTicketTypes(int evId) {
	      DbTicketType dbTt = new DbTicketType();
	      ArrayList<TicketType> allTt = new ArrayList<TicketType>();
	      allTt = dbTt.getAllTicketTypes(evId);
	      return allTt;
	    }
}
