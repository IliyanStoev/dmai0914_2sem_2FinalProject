package modelLayer;

public class Ticket {

	private String barcode;
	private TicketType tt;
	
	public Ticket(String barcode, TicketType tt) {
		this.barcode = barcode;
		this.tt = tt;
	}
	
	public Ticket() {
		
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public TicketType getTt() {
		return tt;
	}

	public void setTt(TicketType tt) {
		this.tt = tt;
	}
	
}
