package modelLayer;

import java.sql.Date;

public class Event {
	
	private int eid;
	private String name;
	private String description;
	private Employee projectManager;
	private Date date;
	private double budget;
	

	public Event() { 
	
	}
	
	public Event(int eid,String name, Employee projectManager, Date date, double budget, String description) {
		
		this.eid = eid;
		this.name = name;
		this.projectManager = projectManager;
		this.date = date;
		this.budget = budget;
		this.description = description;
	}
	
	public Event(int eid) {
		this.eid = eid;
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

	public Employee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
}


