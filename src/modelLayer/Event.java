package modelLayer;

public class Event {
	
	private int eid;
	private String name;
	private String description;
	

public Event() { 

}

public Event(int eid,String name, String description) {
	
	this.eid = eid;
	this.name = name;
	this.description = description;
}

public int getEid() {
	return eid;
}

public void setEid(int eid) {
	this.eid = eid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}
}


