package dbLayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelLayer.Receipt;

public class DbReceipt {

	public int insertReceipt(Receipt rec) {
		String q = "INSERT INTO receipt (receiptNo, rDate, amount) values (?, ?, ?)";
		int res = -1;
		try(PreparedStatement ps = DbConnection.getInstance().getDBcon().prepareStatement(q)
		){			
			ps.setInt(1, rec.getReceiptNo());
			ps.setDate(2, rec.getrDate());
			ps.setDouble(3, rec.getAmount());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
