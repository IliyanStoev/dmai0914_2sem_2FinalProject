package controlLayer;

import java.sql.Date;

import modelLayer.Receipt;
import dbLayer.DbConnection;
import dbLayer.DbReceipt;

public class ReceiptCtr {

	public int registerReceipt(int receiptNo, Date rDate, double amount) throws Exception{
		Receipt rec = new Receipt();
		rec.setReceiptNo(receiptNo);
		rec.setrDate(rDate);
		rec.setAmount(amount);
		int res = -1;
		
		try{
	          DbConnection.startTransaction();
	          DbReceipt dbRec = new DbReceipt();
		      res = dbRec.insertReceipt(rec);
		      
	          DbConnection.commitTransaction();
	         }
	         catch(Exception e)
	         {
	             DbConnection.rollbackTransaction();
	             throw new Exception("Receipt not registered");
	         }
		
		return res;
	}
}
