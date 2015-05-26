package guiLayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modelLayer.Employee;
import modelLayer.Event;
import controlLayer.EmployeeCtr;

public class UserInformation extends JPanel {
	private JTextField fnameTF;
	private JTextField lnameTF;
	private JTextField emailTF;
	private JTextField phoneTF;
	private JTextField cprTF;
	private JTextField companyTF;
	private JTextField positionTF;
	private JTextField textField_7;
	private JList projectsList;

	/**
	 * Create the panel.
	 */
	public UserInformation() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 564, 150);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 30, 70, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(10, 65, 70, 14);
		panel.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 100, 70, 14);
		panel.add(lblEmail);
		
		fnameTF = new JTextField();
		fnameTF.setEditable(false);
		fnameTF.setBounds(70, 27, 180, 20);
		panel.add(fnameTF);
		fnameTF.setColumns(10);
		
		lnameTF = new JTextField();
		lnameTF.setEditable(false);
		lnameTF.setColumns(10);
		lnameTF.setBounds(70, 62, 180, 20);
		panel.add(lnameTF);
		
		emailTF = new JTextField();
		emailTF.setEditable(false);
		emailTF.setColumns(10);
		emailTF.setBounds(70, 97, 180, 20);
		panel.add(emailTF);
		
		JLabel lblPhoneNumber = new JLabel("Phone no.");
		lblPhoneNumber.setBounds(300, 30, 70, 14);
		panel.add(lblPhoneNumber);
		
		JLabel lblCprNo = new JLabel("Cpr no.");
		lblCprNo.setBounds(300, 65, 70, 14);
		panel.add(lblCprNo);
		
		phoneTF = new JTextField();
		phoneTF.setEditable(false);
		phoneTF.setBounds(393, 27, 150, 20);
		panel.add(phoneTF);
		phoneTF.setColumns(10);
		
		cprTF = new JTextField();
		cprTF.setEditable(false);
		cprTF.setBounds(393, 62, 150, 20);
		panel.add(cprTF);
		cprTF.setColumns(10);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				edit();
			}
		});
		btnEdit.setBounds(300, 96, 89, 23);
		panel.add(btnEdit);
		
		JButton btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateEmployee();
			}
		});
		btnSaveChanges.setBounds(423, 96, 120, 23);
		panel.add(btnSaveChanges);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Work Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 172, 564, 160);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(10, 30, 70, 14);
		panel_1.add(lblCompany);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setBounds(10, 65, 70, 14);
		panel_1.add(lblPosition);
		
		companyTF = new JTextField();
		companyTF.setBounds(70, 27, 150, 20);
		panel_1.add(companyTF);
		companyTF.setColumns(10);
		
		positionTF = new JTextField();
		positionTF.setBounds(70, 62, 150, 20);
		panel_1.add(positionTF);
		positionTF.setColumns(10);
		
		JLabel lblProjects = new JLabel("Projects");
		lblProjects.setBounds(270, 30, 70, 14);
		panel_1.add(lblProjects);
		
		projectsList = new JList();
		projectsList.setBounds(342, 30, 200, 119);
		panel_1.add(projectsList);
		
		JButton btnAddProjects = new JButton("Add projects");
		btnAddProjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddProjectsToEmployee.main(null);
			}
		});
		btnAddProjects.setBounds(174, 126, 150, 23);
		panel_1.add(btnAddProjects);
		
		JButton btnRemoveProject = new JButton("Remove project");
		btnRemoveProject.setEnabled(false);
		btnRemoveProject.setBounds(10, 126, 150, 23);
		panel_1.add(btnRemoveProject);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 343, 564, 146);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSendMessageTo = new JLabel("Send message to ");
		lblSendMessageTo.setBounds(10, 14, 120, 14);
		panel_2.add(lblSendMessageTo);
		
		JComboBox<String> msgCB = new JComboBox<>();
		msgCB.setBounds(140, 11, 130, 20);
		panel_2.add(msgCB);
		
		textField_7 = new JTextField();
		textField_7.setBounds(10, 42, 544, 90);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnSendMsg = new JButton("Send message");
		btnSendMsg.setBounds(434, 10, 120, 23);
		panel_2.add(btnSendMsg);

	}
	
	public void fillUserInfo(String fname) {
		EmployeeCtr empCtr = new EmployeeCtr();
		Employee emp = empCtr.findEmployee(fname);
		
		fnameTF.setText(emp.getFname());
		lnameTF.setText(emp.getLname());
		emailTF.setText(emp.getEmail());
		phoneTF.setText(emp.getPhone());
		cprTF.setText(emp.getCpr());
		companyTF.setText(emp.getCompany());
		if (emp.isManager()) {
			positionTF.setText("Manager");
		}
		else { 
			positionTF.setText("Not manager");
		}
		
		DefaultListModel<String> model = new DefaultListModel<>();
		for(Wrapper<Event> val : HelperClass.getEmpEvents(emp)) {
			model.addElement(val.getObject().getName());
		}
		projectsList.setModel(model);
	}
	
	private void updateEmployee() {
		String fname = fnameTF.getText();
		String lname = lnameTF.getText();
		String email = emailTF.getText();
		String phone = phoneTF.getText();
		String cpr = cprTF.getText();
		String password = "default"; 
		int res = -1;
		
		EmployeeCtr empCtr = new EmployeeCtr();
		res = empCtr.updateEmployee(fname, lname, email, phone, cpr, password);
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "The information has been successfully updated");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. The information has not been updated. \n Please try again");
		}
	}
	
	public void edit() {
		fnameTF.setEditable(true);
		lnameTF.setEditable(true);
		emailTF.setEditable(true);
		phoneTF.setEditable(true);
	}
}
