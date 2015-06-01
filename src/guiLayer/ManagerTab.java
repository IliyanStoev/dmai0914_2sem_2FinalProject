package guiLayer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import modelLayer.DateLabelFormatter;
import modelLayer.Employee;
import modelLayer.Event;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlLayer.EmployeeCtr;
import controlLayer.EventCtr;
import controlLayer.TicketTypeCtr;

import javax.swing.SpringLayout;

public class ManagerTab extends JPanel {
	private JTextField rcprTF;
	private JTextField rlnameTF;
	private JComboBox<String> rfnameCB;
	private JComboBox<String> rcompanyCB;
	private JComboBox<String> rprojectCB;
	private JCheckBox chckbxIsManager;
	
	private JComboBox<String> rEvnameCB;
	private JComboBox<Wrapper<Employee>> rprojmanCB;
	private JTextField rdescriptTF;
	private JTextField rbudgetTF;
	private JDatePickerImpl datePicker;
	
	private JComboBox ttEvnameCB;
	private JTextField ttNameTF;
	private JTextField ttAmountTF;
	private JTextField ttPriceTF;
	private JTextField ttDescriptionTF;
	

	/**
	 * Create the panel.
	 */
	public ManagerTab() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Register/Remove user", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 564, 181);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 30, 70, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(10, 65, 70, 14);
		panel.add(lblLastName);
		
		JLabel lblCprNo = new JLabel("Cpr no.");
		lblCprNo.setBounds(10, 100, 70, 14);
		panel.add(lblCprNo);
		
		rcprTF = new JTextField();
		rcprTF.setBounds(90, 97, 150, 20);
		panel.add(rcprTF);
		rcprTF.setColumns(10);
		
		chckbxIsManager = new JCheckBox("Is Manager");
		chckbxIsManager.setBounds(310, 96, 100, 23);
		panel.add(chckbxIsManager);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(310, 30, 70, 14);
		panel.add(lblCompany);
		
		rlnameTF = new JTextField();
		rlnameTF.setBounds(90, 62, 150, 20);
		panel.add(rlnameTF);
		rlnameTF.setColumns(10);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setBounds(310, 65, 70, 14);
		panel.add(lblProject);
		
		rprojectCB = new JComboBox(HelperClass.getEvents().toArray());
		rprojectCB.setSelectedIndex(-1);
		rprojectCB.setBounds(390, 62, 150, 20);
		panel.add(rprojectCB);
		
		JButton btnRegisterEmp = new JButton("Register");
		btnRegisterEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				registerEmployee();
			}
		});
		btnRegisterEmp.setBounds(10, 147, 125, 23);
		panel.add(btnRegisterEmp);
		
		JButton btnClearFieldsEmp = new JButton("Clear fields");
		btnClearFieldsEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFieldsEmp();
			}
		});
		btnClearFieldsEmp.setBounds(220, 147, 125, 23);
		panel.add(btnClearFieldsEmp);
		
		JButton btnRemoveEmp = new JButton("Remove");
		btnRemoveEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeEmployee();
			}
		});
		btnRemoveEmp.setBounds(429, 147, 125, 23);
		panel.add(btnRemoveEmp);
		
		rfnameCB = new JComboBox(HelperClass.getEmployees().toArray());
		rfnameCB.setSelectedIndex(-1);
		rfnameCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rfnameCB.getSelectedIndex() != -1) { 
					Employee emp = (Employee)((Wrapper)rfnameCB.getSelectedItem()).getObject(); 
							
					if (emp != null) {
					rlnameTF.setText(emp.getLname());
					rcprTF.setText(emp.getCpr());
					//chckbxIsManager.setSelected(emp.get);
					}
				}
			}
		});
		rfnameCB.setEditable(true);
		rfnameCB.setBounds(90, 27, 150, 20);
		panel.add(rfnameCB);
		
		rcompanyCB = new JComboBox<>();
		rcompanyCB.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Aalbornification", "Creathio", "Crowd Move"}));
		rcompanyCB.setBounds(390, 27, 150, 20);
		panel.add(rcompanyCB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Register/Remove project", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 203, 564, 160);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 30, 70, 14);
		panel_1.add(lblName);
		
		rEvnameCB = new JComboBox(HelperClass.getEvents().toArray());
		rEvnameCB.setSelectedIndex(-1);
		rEvnameCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rEvnameCB.getSelectedIndex() != -1) {
					Event ev = (Event)((Wrapper<Event>)rEvnameCB.getSelectedItem()).getObject();
					
					if(ev != null) {
						rdescriptTF.setText(ev.getDescription());
						rprojmanCB.setSelectedItem(ev.getProjectManager().getFname());
						datePicker.setVisible(false);
						JTextField date = new JTextField();
						date.setText(""+ev.getDate());
						date.setBounds(120, 93, 150, 25);
						panel_1.add(date);
						rbudgetTF.setText(ev.getBudget() + " DKK");		
					}
				}
			}
		});
		rEvnameCB.setEditable(true);
		rEvnameCB.setBounds(120, 27, 150, 20);
		panel_1.add(rEvnameCB);
		
		JLabel lblProjectManager = new JLabel("Project manager");
		lblProjectManager.setBounds(10, 65, 100, 14);
		panel_1.add(lblProjectManager);
		
		rprojmanCB = new JComboBox(HelperClass.getEmployees().toArray());
		rprojmanCB.setSelectedIndex(-1);
		rprojmanCB.setBounds(120, 62, 150, 20);
		panel_1.add(rprojmanCB);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 100, 70, 14);
		panel_1.add(lblDate);
		
		/*rdateTF = new JTextField();
		rdateTF.setBounds(120, 97, 150, 20);
		panel_1.add(rdateTF);
		rdateTF.setColumns(10);*/
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(1990, 8, 24);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
		datePicker.getJFormattedTextField().setText("Choose date ->");
		datePicker.setBounds(120, 93, 150, 25);
		panel_1.add(datePicker);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(380, 65, 75, 14);
		panel_1.add(lblDescription);
		
		rdescriptTF = new JTextField();
		rdescriptTF.setBounds(280, 93, 274, 58);
		panel_1.add(rdescriptTF);
		rdescriptTF.setColumns(10);
		
		JButton btnRegisterEv = new JButton("Register");
		btnRegisterEv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registerEvent();
			}
		});
		btnRegisterEv.setBounds(10, 128, 125, 23);
		panel_1.add(btnRegisterEv);
		
		JButton btnRemoveEv = new JButton("Remove");
		btnRemoveEv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				removeEvent();
			}
		});
		btnRemoveEv.setBounds(145, 128, 125, 23);
		panel_1.add(btnRemoveEv);
		
		JLabel lblBudget = new JLabel("Budget");
		lblBudget.setBounds(280, 30, 70, 14);
		panel_1.add(lblBudget);
		
		rbudgetTF = new JTextField();
		rbudgetTF.setBounds(360, 27, 100, 20);
		panel_1.add(rbudgetTF);
		rbudgetTF.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Create ticket type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 374, 564, 115);
		add(panel_2);
		panel_2.setLayout(null);
		
		ttEvnameCB = new JComboBox<>(HelperClass.getEvents().toArray());
		ttEvnameCB.setSelectedIndex(-1);
		ttEvnameCB.setToolTipText("Select event");
		ttEvnameCB.setBounds(10, 30, 200, 20);
		panel_2.add(ttEvnameCB);
		
		JLabel lblTypeName = new JLabel("Type name");
		lblTypeName.setBounds(10, 61, 100, 14);
		panel_2.add(lblTypeName);
		
		ttNameTF = new JTextField();
		ttNameTF.setBounds(90, 58, 120, 20);
		panel_2.add(ttNameTF);
		ttNameTF.setColumns(10);
		
		JLabel lblInStock = new JLabel("No. of tickets");
		lblInStock.setBounds(10, 90, 80, 14);
		panel_2.add(lblInStock);
		
		JLabel lblTicketPrice = new JLabel("Ticket price");
		lblTicketPrice.setBounds(220, 90, 80, 14);
		panel_2.add(lblTicketPrice);
		
		JLabel lblTypeDescription = new JLabel("Description");
		lblTypeDescription.setBounds(220, 33, 100, 14);
		panel_2.add(lblTypeDescription);
		
		ttAmountTF = new JTextField();
		ttAmountTF.setBounds(90, 86, 120, 20);
		panel_2.add(ttAmountTF);
		ttAmountTF.setColumns(10);
		
		ttPriceTF = new JTextField();
		ttPriceTF.setBounds(310, 87, 86, 20);
		panel_2.add(ttPriceTF);
		ttPriceTF.setColumns(10);
		
		ttDescriptionTF = new JTextField();
		ttDescriptionTF.setBounds(310, 30, 244, 46);
		panel_2.add(ttDescriptionTF);
		ttDescriptionTF.setColumns(10);
		
		JButton btnRegisterType = new JButton("Register type");
		btnRegisterType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				insertTicketType();
				clearTicketTypeFields();
			}
		});
		btnRegisterType.setBounds(434, 81, 120, 23);
		panel_2.add(btnRegisterType);

	}
	
	/*
	 * Employee methods
	 */
	
	private void registerEmployee() {
		EventCtr evCtr = new EventCtr();
		
		String fname = rfnameCB.getSelectedItem().toString();
		String lname = rlnameTF.getText();
		String cpr = rcprTF.getText();
		boolean  isManager = chckbxIsManager.isSelected();
		String company = rcompanyCB.getSelectedItem().toString();
		Event ev = null;
		if(rprojectCB.getSelectedIndex()!=-1) { 
			
		ev = evCtr.findEvent(rprojectCB.getSelectedItem().toString());
		}
		int res = -1;
		
		
		EmployeeCtr empCtr = new EmployeeCtr();
		try {
		res = empCtr.insertEmployee(fname, lname, cpr, isManager, company, ev);
		}
		catch(Exception e){
	    	System.out.println(e);
	    }
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Employee successfully created");
			clearFieldsEmp();
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe employee was not inserted. \n Please try again");
		}
		}
	
	
	private void removeEmployee() {
		EmployeeCtr empCtr = new EmployeeCtr();
		int res = -1;
		res = empCtr.removeEmployee(rfnameCB.getSelectedItem().toString());
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Employee successfully removed");
			clearFieldsEmp();
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe employee was not removed. \n Please try again");
		}
	}
	
	private void clearFieldsEmp() {
		rfnameCB.setSelectedIndex(-1);
		rlnameTF.setText("");
		rcprTF.setText("");
		rcompanyCB.setSelectedIndex(-1);
		//rprojectCB.setSelectedIndex(-1);
	}
	
	/*
	 * Event methods
	 */
	
	private void registerEvent() {
		EmployeeCtr empCtr = new EmployeeCtr();
		
		String name = rEvnameCB.getSelectedItem().toString();
		Employee projectManager =  empCtr.findEmployee(rprojmanCB.getSelectedItem().toString()); // finding the employee with the name chosen in the comboBox
		java.util.Date dateU = (java.util.Date) datePicker.getModel().getValue(); //getting the value 
		java.sql.Date date = new java.sql.Date(dateU.getTime());					// from the date picker
		double budget = Double.parseDouble(rbudgetTF.getText());
		String description = rdescriptTF.getText();
		
		int res = -1;
		
		EventCtr evCtr = new EventCtr();
		try {
		res = evCtr.insertEvent(name, projectManager, date, budget, description);
		}
		catch(Exception e){
	    	System.out.println(e);
	    }
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Event successfully created");
			clearFieldsEv();
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe event was not inserted. \n Please try again");
		}
	}
	
	private void removeEvent() {
		EventCtr evCtr = new EventCtr();
		Event ev = evCtr.findEvent(rEvnameCB.getSelectedItem().toString());
		int res = -1;
		res = evCtr.removeEvent(ev.getEid());
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Event successfully removed");
			clearFieldsEv();
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe event was not removed. \n Please try again");
		}
	}
	
	private void clearFieldsEv() {
		rEvnameCB.setSelectedIndex(-1);
		rprojmanCB.setSelectedIndex(-1);
		rbudgetTF.setText("");
		rdescriptTF.setText("");
	}
	
	/*
	 * Ticket type methods
	 */
	
	private void insertTicketType() {
		EventCtr evCtr = new EventCtr();
		String evname = ttEvnameCB.getSelectedItem().toString();
		
		Event ev = evCtr.findEvent(evname);
		String name = ttNameTF.getText();
		int inStock = Integer.parseInt(ttAmountTF.getText());
		double price = Double.parseDouble(ttPriceTF.getText());
		String description = ttDescriptionTF.getText();
		
		int res = -1;
		
		TicketTypeCtr ttCtr = new TicketTypeCtr();
		try{
			res = ttCtr.insertTicketType(ev, name, inStock, price, description);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Type successfully inserted");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe type has not been created. \nPlease try again");
		}
	}
	
	public void clearTicketTypeFields() {
		ttEvnameCB.setSelectedIndex(-1);
		ttNameTF.setText("");
		ttAmountTF.setText("");
		ttDescriptionTF.setText("");
		ttPriceTF.setText("");
	}
}
