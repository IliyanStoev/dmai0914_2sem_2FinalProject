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
		String q = "INSERT INTO ticket (barcode, bid) values (?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q)
		){			
			ps.setString(1, t.getBarcode());
			ps.setInt(2, t.getBook().getB_id());
			
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
		
	public int removeTickets(Booking book) {
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement("delete from ticket where bid = ?")) {
			ps.setInt(1, book.getB_id());
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
			t.setBook(new Booking(rs.getInt("bid")));
			
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
						DbBooking dbBook = new DbBooking();
						int bid = t.getBook().getB_id();
                        Booking book = dbBook.singleWhere(" bid = '" + bid + "'", false);
                        t.setBook(book);
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

 		Ticket t = findTicket(barcode);

		if( t == null ) {
			//System.out.println("if = true");
			return barcode;
		}
		else {
			//System.out.println("if = false");
			generateBarcode(evName);
			return null;
		}
	}
}
