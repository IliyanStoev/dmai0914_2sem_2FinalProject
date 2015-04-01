package modelLayer;

public class Employee {
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String cpr;
	private String password;
	private int tokens;
	
	public Employee() {
		
	}
	
	public Employee(String fname, String  lname, String  phone, String  cpr) {
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
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
	
}
