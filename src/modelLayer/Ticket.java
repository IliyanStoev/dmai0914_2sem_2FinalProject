package modelLayer;

public class Ticket {

	private String barcode;
	private Booking book;
	
	public Ticket(String barcode, Booking book) {
		this.barcode = barcode;
		this.book = book;;
	}
	
	public Ticket() {
		
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/*public TicketType getTt() {
		return tt;
	}

	public void setTt(TicketType tt) {
		this.tt = tt;
	}
	*/
	
	public Booking getBook() { 
		return book;
	}
	
	public void setBook(Booking book) {
		
		this.book = book;
	}

	
	
	
}
