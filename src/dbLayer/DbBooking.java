package dbLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelLayer.Booking;
import modelLayer.Employee;
import modelLayer.Ticket;
import modelLayer.TicketType;

public class DbBooking {

	public int insertBooking(Booking book) {
		String q = "INSERT INTO booking (bdate, status, total, payType, gname, gphone, gemail, quantity, ttid, cpr) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q, Statement.RETURN_GENERATED_KEYS)
		){			
			ps.setDate(1, book.getBdate());
			ps.setString(2, book.getStatus());
			ps.setDouble(3, book.getTotal());
			ps.setInt(4, book.getPayType());
			ps.setString(5, book.getGname());
			ps.setString(6, book.getGphone());
			ps.setString(7, book.getGemail());
			ps.setInt(8, book.getQuantity());
			ps.setInt(9, book.getTt().getTtId());
			ps.setString(10, book.getEmp().getCpr());
			res = ps.executeUpdate();
			int id = new GeneratedKey().getGeneratedKey(ps);
			book.setB_id(id);
			
			DbTicketType dbTt = new DbTicketType();
			if(res != -1) {
				res = dbTt.updateTicketTypeStock(book.getQuantity(), book.getTt().getTtId());
			}
			
			DbTicket dbT = new DbTicket();
			for(int i=0; i<book.getQuantity(); i++) {
				if(res != -1) {
					Ticket t = dbT.generateTicket(book.getTt().getEv().getName());
					t.setTt(book.getTt());
					res = dbT.insertTicket(t);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Booking findBooking(String phone) {
		String w = "gphone = '" + phone + "'";
		Booking book = this.singleWhere(w, true);
		return book;
	}
	
	public int cancelBooking(Booking book) {
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement("update booking set status = 'Cancelled' where bid = ?")) {
			ps.setInt(1, book.getB_id());
			res = ps.executeUpdate();
			
			DbTicketType dbTt = new DbTicketType();
			if(res != -1) {
				res = dbTt.updateTicketTypeStock(-book.getQuantity(), book.getTt().getTtId());
				
				DbTicket dbT = new DbTicket();
				if (res != -1) { 
					res = dbT.removeTickets(book);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	private String buildQuery(String where) {
		String q = "select * from booking";
		if(where != null && where.trim().length() > 0) {
			q += " where " + where;
		}
		return q;
	}
	
	private Booking buildBooking(ResultSet rs) {
		Booking book = null;
		try {
			book = new Booking();
			book.setB_id(rs.getInt("bid"));
			book.setBdate(rs.getDate("bdate"));
			book.setStatus(rs.getString("status"));
			book.setTotal(rs.getDouble("total"));
			book.setPayType(rs.getInt("payType"));
			book.setQuantity(rs.getInt("quantity"));
			book.setGname(rs.getString("gname"));
			book.setGphone(rs.getString("gphone"));
			book.setGemail(rs.getString("gemail"));
			book.setTt(new TicketType(rs.getInt("ttid")));
			book.setEmp(new Employee(rs.getString("cpr")));
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	private Booking singleWhere(String where, boolean retrieveAssociation) {
		List<Booking> res = miscWhere(where, retrieveAssociation);
		if(res.size() > 0) {
			return res.get(0);
		}
		return null;
	}
	
	private ArrayList<Booking> miscWhere(String where, boolean retrieveAssociation) {
		ArrayList<Booking> res = new ArrayList<>();
		try(Statement s = DbConnection.getInstance().getDBcon().createStatement()
		) {
			String q = buildQuery(where);
			ResultSet rs = s.executeQuery(q);
			Booking book = null;
			while(rs.next()) {
				book = buildBooking(rs);
				
					if(retrieveAssociation) {
						DbTicketType dbTt = new DbTicketType();
						int ttId = book.getTt().getTtId();
                        TicketType tt = dbTt.singleWhere(" ttid = '" + ttId + "'", true);
                        book.setTt(tt);
                        
                        DbEmployee dbEmp = new DbEmployee();
                        String cpr = book.getEmp().getCpr();
                        Employee emp = dbEmp.singleWhere(" cpr = '" + cpr +"'", false);
                        book.setEmp(emp);
					}
				res.add(book);
			}
			rs.close();
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
