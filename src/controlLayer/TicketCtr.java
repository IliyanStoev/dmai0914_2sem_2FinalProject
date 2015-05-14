package controlLayer;

import java.util.Random;

import modelLayer.Ticket;
import modelLayer.TicketType;
import dbLayer.DbConnection;
import dbLayer.DbTicket;

public class TicketCtr {

	public int insertTicket(String barcode, TicketType tt) throws Exception{
		Ticket t = new Ticket();
		t.setBarcode(barcode);
		t.setTt(tt);
		int res = -1;
		
		try{
	          DbConnection.startTransaction();
	          DbTicket dbT = new DbTicket();
		      res = dbT.insertTicket(t);
	          DbConnection.commitTransaction();
	         }
	         catch(Exception e)
	         {
	             DbConnection.rollbackTransaction();
	             throw new Exception("Ticket not registered");
	         }
		
		return res;
	}
	
	public Ticket findTicket(String barcode) {
		DbTicket dbT = new DbTicket();
		Ticket t = dbT.findTicket(barcode);
		
		return t;
	}
	
	public Ticket generateTicket(String ttName) {
		String barcode = generateBarcode(ttName);
		//int res = -1;
		Ticket t = new Ticket();
		t.setBarcode(barcode);
		
		return t;
	}
	
	private String generateBarcode(String ttName) {
		Random rand = new Random();
		int nr = rand.nextInt(10000)+1000;
 		String barcode = ttName.substring(0, 2) + nr;
 		
 		TicketCtr tCtr = new TicketCtr();
 		Ticket t = tCtr.findTicket(barcode);
		if( t == null ) {
			System.out.println("if = true");
			return barcode;
		}
		else {
			System.out.println("if = false");
			generateBarcode(ttName);
			return null;
		}
	}
}
