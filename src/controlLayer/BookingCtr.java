package controlLayer;

import java.sql.Connection;
import java.sql.Date;

import modelLayer.Booking;
import modelLayer.Employee;
import modelLayer.TicketType;
import dbLayer.DbBooking;
import dbLayer.DbConnection;

public class BookingCtr {

	public int registerBooking(Date bdate, Employee emp, TicketType tt, int quantity, double total, String gname, String gphone, String gemail, int payType, String status) throws Exception{
		Booking book = new Booking();
		book.setBdate(bdate);
		book.setEmp(emp);
		book.setTt(tt);
		book.setQuantity(quantity);
		book.setTotal(total);
		book.setGname(gname);
		book.setGphone(gphone);
		book.setGemail(gemail);
		book.setPayType(payType);
		book.setStatus(status);
		int res = -1;
		
		try{
			Connection con = DbConnection.getInstance().getDBcon();
			con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
			
			
	          DbConnection.startTransaction();
	          DbBooking dbBook = new DbBooking();
		      res = dbBook.insertBooking(book);
	          DbConnection.commitTransaction();
	         }
	         catch(Exception e)
	         {
	             DbConnection.rollbackTransaction();
	             throw new Exception("Booking not registered");
	         }
		
		return res;
	}
	
	public Booking findBooking(String phone) {
		DbBooking dbBook = new DbBooking();
		Booking book = dbBook.findBooking(phone);
		
		return book;
	}
	
	
	public int cancelBooking(String phone) {
		DbBooking dbBook = new DbBooking();
		Booking book = findBooking(phone);
		int res = -1;
		
		res = dbBook.cancelBooking(book);
		
		return res;
	}
}
