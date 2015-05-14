package modelLayer;

import java.util.ArrayList;

public class Employee {
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String cpr;
	private String password;
	private int tokens;
	private boolean isManager;
	private String company;
	//private ArrayList<String> worksOn;
	private Event ev;
	
	public Employee() {
		
	}
	
	public Employee(String fname, String  lname, String  cpr, boolean isManager, String company, Event ev) {
		this.fname = fname;
		this.lname = lname;
		this.cpr = cpr;
		this.isManager = isManager;
		this.company = company;
		this.ev = ev;
		//this.worksOn = project;
	}
	
	public Employee(String cpr) {
		this.cpr = cpr;
	}
	
	public Employee(String name, String cpr) {
		this.fname = name;
		this.cpr = cpr;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}	
	
	public boolean isManager() {
		return isManager;
	}

	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Event getEv() {
		return ev;
	}

	public void setEv(Event ev) {
		this.ev = ev;
	}	
	
}
