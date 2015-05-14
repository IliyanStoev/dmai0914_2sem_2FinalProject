package controlLayer;

import java.util.Date;

import modelLayer.Employee;
import modelLayer.TicketType;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterBookingJUnitTest {

	Date date = new Date();
	java.sql.Date bdate = new java.sql.Date(date.getTime());
	
	EmployeeCtr empCtr = new EmployeeCtr();
	Employee emp = empCtr.findEmployee("Boril");
	
	TicketTypeCtr ttCtr = new TicketTypeCtr();
	TicketType tt = ttCtr.findTicketType("Rio Fever");

	int res = -1;
	
	@Test
	public void testValid() {
		BookingCtr bookCtr = new BookingCtr();
		try{
			res = bookCtr.registerBooking(bdate, emp, tt, 2, 2*tt.getPrice(), "Boril", "223445143", "boril@ucn.dk", 1, "Paid");
		}
		catch(Exception e){
	    	System.out.println(e);
	    }
		
		assertEquals(1, res);
	}
	
	/*@Test
	public void testIllegalName() {
		BookingCtr bookCtr = new BookingCtr();
		try {
			res = bookCtr.registerBooking(bdate, emp, tt, 2, 2*tt.getPrice(), "Boril32", "223445143", "boril@ucn.dk", 1, "Paid");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		assertEquals(1, res);
	}*/

}
