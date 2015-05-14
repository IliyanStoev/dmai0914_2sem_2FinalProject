package controlLayer;

import static org.junit.Assert.assertEquals;
import modelLayer.Event;

import org.junit.Test;

public class TestEmployee {

	int res = -1;
	
	@Test
	public void testInsertEmployee() {
		EmployeeCtr empCtr = new EmployeeCtr();
		
		try{
			res = empCtr.insertEmployee("Joe", "Smith", "2000000000", true, "Aalbornification", new Event(101));
		} 
		catch(Exception e) {
			System.out.println(e);
		}
		
		assertEquals(1, res);
	}

}
