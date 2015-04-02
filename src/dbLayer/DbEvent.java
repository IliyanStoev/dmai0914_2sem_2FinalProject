package dbLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbLayer.GeneratedKey;
import modelLayer.Event;


public class DbEvent {

	public int insertEvent(Event ev) {
		String q = "INSERT INTO event (name, description) values (?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q, Statement.RETURN_GENERATED_KEYS)
		){
			
			
			ps.setString(1, ev.getName());
			ps.setString(2, ev.getDescription());
		
			
			res = ps.executeUpdate();
			int id = new GeneratedKey().getGeneratedKey(ps);
			ev.setEid(id);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Event findEvent(String name) {
		String w = "name = '" + name + "'";
		Event ev = this.singleWhere(w);
		return ev;
	}
	
		public int updateEvent(Event ev) {
			int res = -1;
			if( ev != null ) {
				String q = "update event set "
						+ "name='" + ev.getName() + "', "
							     + "description='" + ev.getDescription() + "' "
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
			ev.setDescription(rs.getString("description"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}
	
	private Event singleWhere(String where) {
		List<Event> res = miscWhere(where);
		if(res.size() > 0) {
			return res.get(0);
		}
		return null;
	}
	
	private List<Event> miscWhere(String where) {
		List<Event> res = new ArrayList<>();
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			String q = buildQuery(where);
			ResultSet rs = s.executeQuery(q);
			Event ev = null;
			while(rs.next()) {
				ev = buildEvent(rs);
				res.add(ev);
			}
			rs.close();
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
