package controlLayer;

import dbLayer.*;
import modelLayer.Employee;

public class EmployeeCtr {

	public int insertEmployee(String fname, String lname, String phone, String cpr) throws Exception{
		Employee emp = new Employee();
		emp.setFname(fname);
		emp.setLname(lname);
		emp.setPhone(phone);
		emp.setCpr(cpr);
		emp.setPassword("default");
		int res = -1;
		
		try{
	          DbConnection.startTransaction();
	          DbEmployee dbEmp = new DbEmployee();
		      res = dbEmp.insertEmployee(emp);
	          DbConnection.commitTransaction();
	         }
	         catch(Exception e)
	         {
	             DbConnection.rollbackTransaction();
	             throw new Exception("Product not inserted");
	         }
		
		return res;
	}
	
	public Employee findEmployee(String fname) {
		DbEmployee dbEmp = new DbEmployee();
		Employee emp = dbEmp.findEmployee(fname);
		
		return emp;
	}
	
	public int updateEmployee(String fname, String lname, String email, String phone, String cpr, String password) {
		DbEmployee dbEmp = new DbEmployee();
		Employee emp = new Employee();
		int res = -1;
		
		emp.setFname(fname);
		emp.setLname(lname);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setCpr(cpr);
		emp.setPassword(password);
		
		res = dbEmp.updateEmployee(emp);
		
		return res;
	}
	
	public int removeEmployee(String cpr) {
		DbEmployee dbEmp = new DbEmployee();
		Employee emp = new Employee();
		emp.setCpr(cpr);
		int res = -1;
		
		res = dbEmp.removeEmployee(emp);
		
		return res;
	}
}
