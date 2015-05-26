package guiLayer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelLayer.Employee;
import modelLayer.Event;
import modelLayer.TicketType;
import controlLayer.EmployeeCtr;
import controlLayer.EventCtr;
import controlLayer.TicketTypeCtr;

public class HelperClass {

	public HelperClass () {
		
	}
	
	public static ArrayList<Wrapper<Employee>> getEmployees() {
		EmployeeCtr empCtr = new EmployeeCtr();
		ArrayList<Employee> empList = empCtr.findAllEmployee();
		ArrayList<Wrapper<Employee>> wrapEmps = new ArrayList<>();
		for(Employee emp : empList) {
			wrapEmps.add(new Wrapper<Employee>(emp, () -> emp.getFname()));
		}
		return wrapEmps;
	}
	
	public static ArrayList<Wrapper<Event>> getEvents() {
		EventCtr evCtr = new EventCtr();
		ArrayList<Event> evList = evCtr.findAllEvents();
		ArrayList<Wrapper<Event>> wrapEv = new ArrayList<>();
		for(Event ev : evList) {
			wrapEv.add(new Wrapper<Event>(ev, () -> ev.getName()));
		}
		return wrapEv;
	}
	
	public static ArrayList<Wrapper<TicketType>> getTicketTypes(int evId) {
		TicketTypeCtr ttCtr = new TicketTypeCtr();
		ArrayList<TicketType> ttList = ttCtr.findAllTicketTypes(evId);
		ArrayList<Wrapper<TicketType>> wraptt = new ArrayList<>();
		for(TicketType tt : ttList) {
			wraptt.add(new Wrapper<TicketType>(tt, () -> tt.getPrice() + " dkk - " + tt.getName()));
			//System.out.println(tt.getEv().getName());
		}
		return wraptt;
	}
	
	public static ArrayList<Wrapper<Event>> getEmpEvents(Employee emp) {
		EventCtr evCtr = new EventCtr();
		ArrayList<Event> empEvList = evCtr.findEmpEvents(emp);
		ArrayList<Wrapper<Event>> empEvents = new ArrayList<>();
		for(Event ev : empEvList) {
			empEvents.add(new Wrapper<Event>(ev, () -> ev.getName()));
		}
		
		return empEvents;
	}
	
	public static boolean nameIsNotValid(String name) {
		Pattern p = Pattern.compile("([0-9])");
		Matcher m = p.matcher(name);
		
		return m.find();
	}
	
	public static boolean phoneIsNotValid(String phone) {
		Pattern p = Pattern.compile("\\D");
		Matcher m = p.matcher(phone);
		
		return m.find();
	}
	
	public static boolean emailIsValid(String email) {
		Pattern p = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
		Matcher m = p.matcher(email);
		
		return m.find();
	}
	
}
