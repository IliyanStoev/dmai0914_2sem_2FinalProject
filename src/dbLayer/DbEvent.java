package dbLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbLayer.GeneratedKey;
import modelLayer.Employee;
import modelLayer.Event;


public class DbEvent {

	public int insertEvent(Event ev) {
		String q = "INSERT INTO event (name, cpr, evDate, budget, description) values (?, ?, ?, ?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q, Statement.RETURN_GENERATED_KEYS)
		){			
			ps.setString(1, ev.getName());
			ps.setString(2, ev.getProjectManager().getCpr());
			ps.setDate(3, ev.getDate());
			ps.setDouble(4, ev.getBudget());
			ps.setString(5, ev.getDescription());
			
			res = ps.executeUpdate();
			int id = new GeneratedKey().getGeneratedKey(ps);
			ev.setEid(id);
			
			if(res != -1) {
				res = insertEmpEvent(ev, ev.getProjectManager());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Event findEvent(String name) {
		String w = "name = '" + name + "'";
		Event ev = this.singleWhere(w, true);
		return ev;
	}
	
	public Event findEventById(int eid) {
		String w = "eid = '" + eid + "'";
		Event ev = this.singleWhere(w, true);
		return ev;
	}
	
	public ArrayList<Event> getAllEvents(){
        return miscWhere("", true);
    }
	
	public int updateEvent(Event ev) {
		int res = -1;
		if( ev != null ) {
			String q = "update event set "
					+ "name='" + ev.getName() + "', "
						  	+ "description='" + ev.getDescription() + "', "
						     	+ "budget = '" + ev.getBudget() + "' "
					+ "where eid = '" + ev.getEid() + "'";
			try(Statement s = DbConnection.getInstance().getDBcon().createStatement()) {
				res = s.executeUpdate(q);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
		
	public int removeEvent(Event ev) {
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement("delete from event where eid = ?")) {
			ps.setInt(1, ev.getEid());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private String buildQuery(String where) {
		String q = "select * from event";
		if(where != null && where.trim().length() > 0) {
			q += " where " + where;
		}
		return q;
	}
	
	private Event buildEvent(ResultSet rs) {
		Event ev = null;
		try {
			ev = new Event();
			ev.setEid(rs.getInt("eid"));
			ev.setName(rs.getString("name"));
			ev.setProjectManager(new Employee(rs.getString("cpr")));
			ev.setDescription(rs.getString("description"));
			ev.setBudget(rs.getDouble("budget"));
			ev.setDate(rs.getDate("evDate"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}
	
	public Event singleWhere(String where, boolean retrieveAssociation) {
		List<Event> res = miscWhere(where, retrieveAssociation);
		if(res.size() > 0) {
			return res.get(0);
		}
		return null;
	}
	
	private ArrayList<Event> miscWhere(String where, boolean retrieveAssociation) {
		ArrayList<Event> res = new ArrayList<>();
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			String q = buildQuery(where);
			ResultSet rs = s.executeQuery(q);
			Event ev = null;
			while(rs.next()) {
				ev = buildEvent(rs);
				
					if(retrieveAssociation) {
						DbEmployee dbEmp = new DbEmployee();
						String cpr = ev.getProjectManager().getCpr();
                        Employee projManager = dbEmp.singleWhere(" cpr = '" + cpr + "'" , false);
                        ev.setProjectManager(projManager);
					}
				res.add(ev);
			}
			rs.close();
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * Employee - event association methods
	 */
	
	public int insertEmpEvent(Event ev, Employee emp) {
		int res = -1;
		String q = "INSERT INTO empEvent (cpr, eid) values (?, ?)";
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q)
		){
			ps.setString(1, emp.getCpr());
			ps.setInt(2, ev.getEid());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<Event> getEmpEvents(String cpr) {
		String w = "SELECT * FROM empEvent where cpr = '" + cpr + "'";
		ArrayList<Event> res = new ArrayList<>();
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			ResultSet rs = s.executeQuery(w);
			while(rs.next()){
				Event ev = findEventById(rs.getInt("eid"));
				res.add(ev);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean isDuplicate(Event ev, Employee emp) {
		boolean isDuplicate = false;
		String w = "SELECT * FROM empEvent where cpr = '" + emp.getCpr() + "' AND eid = '" + ev.getEid() + "'";
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			ResultSet rs = s.executeQuery(w);
			if(rs.next()) {
				isDuplicate = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return isDuplicate;
	}
	
}
