package dbLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelLayer.Booking;
import modelLayer.Employee;
import modelLayer.Event;
import modelLayer.TicketType;

public class DbTicketType {

	public int insertTicketType(TicketType tt) {
		String q = "INSERT INTO ticketType (name, description, price, inStock, eid) values (?, ?, ?, ?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q, Statement.RETURN_GENERATED_KEYS)
		){			
			ps.setString(1, tt.getName());
			ps.setString(2, tt.getDescription());
			ps.setDouble(3, tt.getPrice());
			ps.setInt(4, tt.getInStock());
			ps.setInt(5, tt.getEv().getEid());
			
			res = ps.executeUpdate();
			int id = new GeneratedKey().getGeneratedKey(ps);
			tt.setTtId(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public TicketType findTicketType(String name) {
		String w = "name = '" + name + "'";
		TicketType tt = this.singleWhere(w, true);
		return tt;
	}
	
	private TicketType findTicketType(int ttId) {
		String w = "ttid = '" + ttId + "'";
		TicketType tt = this.singleWhere(w, true);
		return tt;
	}
	
	public ArrayList<TicketType> getAllTicketTypes(int evId){
		if(evId == -1) {
			return miscWhere("", false);
		}
		else {
			return miscWhere("eid = '" + evId + "'", false);
		}
    }
	
	public int updateTicketTypeStock(int quantity, int ttId) {
		int res = -1;
		if( quantity != 0 ) {
			if (findTicketType(ttId).getInStock() > quantity ) {
				String q = "update ticketType set "
						+ "inStock = inStock -'" + quantity + "' "
							+ "where ttid = '" + ttId + "'";
				try(Statement s = DbConnection.getInstance().getDBcon().createStatement()) {
					res = s.executeUpdate(q);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
		
		/*public int removeEvent(Event ev) {
			int res = -1;
			try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement("delete from event where eid = ?")) {
				ps.setInt(1, ev.getEid());
				res = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return res;
		}*/
	
	private String buildQuery(String where) {
		String q = "select * from ticketType";
		if(where != null && where.trim().length() > 0) {
			q += " where " + where;
		}
		return q;
	}
	
	private TicketType buildTicketType(ResultSet rs) {
		TicketType tt = null;
		try {
			tt = new TicketType();
			tt.setTtId(rs.getInt("ttid"));
			tt.setEv(new Event(rs.getInt("eid")));
			tt.setName(rs.getString("name"));
			tt.setInStock(rs.getInt("inStock"));
			tt.setPrice(rs.getDouble("price"));
			tt.setDescription(rs.getString("description"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tt;
	}
	
	public TicketType singleWhere(String where, boolean retrieveAssociation) {
		List<TicketType> res = miscWhere(where, retrieveAssociation);
		if(res.size() > 0) {
			return res.get(0);
		}
		return null;
	}
	
	private ArrayList<TicketType> miscWhere(String where, boolean retrieveAssociation) {
		ArrayList<TicketType> res = new ArrayList<>();
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			String q = buildQuery(where);
			ResultSet rs = s.executeQuery(q);
			TicketType tt = null;
			while(rs.next()) {
				tt = buildTicketType(rs);
				
					if(retrieveAssociation) {
						DbEvent dbEv = new DbEvent();
						int eid = tt.getEv().getEid();
                        Event ev = dbEv.singleWhere(" eid = '" + eid + "'", false);
                        tt.setEv(ev);
					}
				res.add(tt);
			}
			rs.close();
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
