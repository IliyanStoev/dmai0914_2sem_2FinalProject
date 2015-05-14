package dbLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelLayer.Booking;
import modelLayer.Ticket;
import modelLayer.TicketType;

public class DbTicket {

	public int insertTicket(Ticket t) {
		String q = "INSERT INTO ticket (barcode, ttid) values (?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q)
		){			
			ps.setString(1, t.getBarcode());
			ps.setInt(2, t.getTt().getTtId());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Ticket findTicket(String barcode) {
		String w = "barcode = '" + barcode + "'";
		Ticket t = this.singleWhere(w, true);
		return t;
	}
	
	/*public ArrayList<Event> getAllEvents(){
        return miscWhere("", false);
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
	}*/
		
	public int removeTickets(Booking book) {
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement("delete from ticket where ttid = ?")) {
			ps.setInt(1, book.getTt().getTtId());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private String buildQuery(String where) {
		String q = "select * from ticket";
		if(where != null && where.trim().length() > 0) {
			q += " where " + where;
		}
		return q;
	}
	
	private Ticket buildTicket(ResultSet rs) {
		Ticket t = null;
		try {
			t = new Ticket();
			t.setBarcode(rs.getString("barcode"));
			t.setTt(new TicketType(rs.getInt("ttid")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public Ticket singleWhere(String where, boolean retrieveAssociation) {
		List<Ticket> res = miscWhere(where, retrieveAssociation);
		if(res.size() > 0) {
			return res.get(0);
		}
		return null;
	}
	
	private ArrayList<Ticket> miscWhere(String where, boolean retrieveAssociation) {
		ArrayList<Ticket> res = new ArrayList<>();
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			String q = buildQuery(where);
			ResultSet rs = s.executeQuery(q);
			Ticket t = null;
			while(rs.next()) {
				t = buildTicket(rs);
				
					if(retrieveAssociation) {
						DbTicketType dbTt = new DbTicketType();
						int ttid = t.getTt().getTtId();
                        TicketType tt = dbTt.singleWhere(" ttid = '" + ttid + "'", false);
                        t.setTt(tt);
					}
				res.add(t);
			}
			rs.close();
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * Generate barcode and ticket
	 */

	public Ticket generateTicket(String evName) {
		String barcode = generateBarcode(evName);
		//int res = -1;
		Ticket t = new Ticket();
		t.setBarcode(barcode);
		
		return t;
	}
	
	private String generateBarcode(String evName) {
		Random rand = new Random();
		int nr = rand.nextInt(10000)+1000;
 		String barcode = evName.substring(0, 2) + nr;
 		
 		//TicketCtr tCtr = new TicketCtr();
 		Ticket t = findTicket(barcode);
		if( t == null ) {
			System.out.println("if = true");
			return barcode;
		}
		else {
			System.out.println("if = false");
			generateBarcode(evName);
			return null;
		}
	}
}
