package dbLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelLayer.Employee;

public class DbEmployee {

	public int insertEmployee(Employee emp) {
		String q = "insert into employee (fname, lname, phone, cpr, password, isManager, company) values (?, ?, ?, ?, ?, ?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q)
		){
			ps.setString(1, emp.getFname());
			ps.setString(2, emp.getLname());
			ps.setString(3, emp.getPhone());
			ps.setString(4, emp.getCpr());
			ps.setString(5, emp.getPassword());
			ps.setBoolean(6, emp.isManager());
			ps.setString(7, emp.getCompany());
			
			res = ps.executeUpdate();
			
			if (res != -1 && emp.getEv() != null) {
				DbEvent dbEv = new DbEvent();
				
				res = dbEv.insertEmpEvent(emp.getEv(), emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Employee findEmployee(String fname) {
		String w = "fname = '" + fname + "'";
		Employee emp = this.singleWhere(w, true);
		return emp;
	}
	
	public ArrayList<Employee> getAllEmployees(){
        return miscWhere("", true);
    }
	
	public int updateEmployee(Employee emp) {
		int res = -1;
		if( emp != null ) {
			String q = "update employee set "
					+ "fname='" + emp.getFname() + "', "
							+ "lname='" + emp.getLname() + "', "
								+ "email='" + emp.getEmail() + "', "
									+ "phone='" + emp.getPhone() + "', "
										+ "password='" + emp.getPassword() + "' "
					+ "where cpr = " + emp.getCpr();
			try(Statement s = DbConnection.getInstance().getDBcon().createStatement()) {
				res = s.executeUpdate(q);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public int removeEmployee(Employee emp) {
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement("delete from employee where fname = ?")) {
			ps.setString(1, emp.getFname());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private String buildQuery(String where) {
		String q = "select * from employee";
		if(where != null && where.trim().length() > 0) {
			q += " where " + where;
		}
		return q;
	}
	
	private Employee buildEmployee(ResultSet rs) {
		Employee emp = null;
		try {
			emp = new Employee();
			emp.setFname(rs.getString("fname"));
			emp.setLname(rs.getString("lname"));
			emp.setEmail(rs.getString("email"));
			emp.setPhone(rs.getString("phone"));
			emp.setCpr(rs.getString("cpr"));
			emp.setPassword(rs.getString("password"));
			emp.setTokens(rs.getInt("tokens"));
			emp.setIsManager(rs.getBoolean("isManager"));
			emp.setCompany(rs.getString("company"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public Employee singleWhere(String where, boolean retrieveAssociation) {
		List<Employee> res = miscWhere(where, retrieveAssociation);
		if(res.size() > 0) {
			return res.get(0);
		}
		return null;
	}
	
	private ArrayList<Employee> miscWhere(String where, boolean retrieveAssociation) {
		ArrayList<Employee> res = new ArrayList<>();
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			String q = buildQuery(where);
			ResultSet rs = s.executeQuery(q);
			Employee emp = null;
			while(rs.next()) {
				emp = buildEmployee(rs);
				res.add(emp);
			}
			rs.close();
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
