package modelLayer;

public class TicketType {

	private Event ev;
	private String name;
	private int inStock;
	private String description;
	private double price;
	private int ttId;
	
	public TicketType(Event ev, String name, int inStock, String description, double price) {
		this.ev = ev;
		this.name = name;
		this.inStock = inStock;
		this.description = description;
		this.price = price;
	}
	
	public TicketType(int ttId) {
		this.ttId = ttId;
	}
	
	public TicketType() {
		
	}

	public Event getEv() {
		return ev;
	}

	public void setEv(Event ev) {
		this.ev = ev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTtId() {
		return ttId;
	}

	public void setTtId(int ttId) {
		this.ttId = ttId;
	}
	
}
