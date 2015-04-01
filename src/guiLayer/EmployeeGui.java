package guiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import modelLayer.Employee;
import controlLayer.EmployeeCtr;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeGui extends JFrame {

	private JPanel contentPane;
	private JTextField fnameTF;
	private JTextField lnameTF;
	private JTextField emailTF;
	private JTextField phoneTF;
	private JTextField cprTF;
	private JTextField passwordTF;
	private JTextField tokensTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeGui frame = new EmployeeGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 29, 81, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(10, 60, 81, 14);
		contentPane.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 97, 81, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 134, 81, 14);
		contentPane.add(lblPhone);
		
		JLabel lblCpr = new JLabel("Cpr");
		lblCpr.setBounds(10, 170, 81, 14);
		contentPane.add(lblCpr);
		
		fnameTF = new JTextField();
		fnameTF.setBounds(80, 26, 169, 20);
		contentPane.add(fnameTF);
		fnameTF.setColumns(10);
		
		lnameTF = new JTextField();
		lnameTF.setColumns(10);
		lnameTF.setBounds(80, 57, 169, 20);
		contentPane.add(lnameTF);
		
		emailTF = new JTextField();
		emailTF.setColumns(10);
		emailTF.setBounds(80, 94, 169, 20);
		contentPane.add(emailTF);
		
		phoneTF = new JTextField();
		phoneTF.setColumns(10);
		phoneTF.setBounds(80, 131, 169, 20);
		contentPane.add(phoneTF);
		
		cprTF = new JTextField();
		cprTF.setColumns(10);
		cprTF.setBounds(80, 167, 169, 20);
		contentPane.add(cprTF);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				insertEmployee();
			}
		});
		btnInsert.setBounds(10, 213, 89, 23);
		contentPane.add(btnInsert);
		
		JButton btnFind = new JButton("Find");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				findEmployee();
			}
		});
		btnFind.setBounds(119, 213, 89, 23);
		contentPane.add(btnFind);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateEmployee();
			}
		});
		btnUpdate.setBounds(232, 213, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeEmployee();
			}
		});
		btnRemove.setBounds(335, 213, 89, 23);
		contentPane.add(btnRemove);
		
		passwordTF = new JTextField();
		passwordTF.setBounds(301, 57, 123, 20);
		contentPane.add(passwordTF);
		passwordTF.setColumns(10);
		
		tokensTF = new JTextField();
		tokensTF.setColumns(10);
		tokensTF.setBounds(378, 94, 46, 20);
		contentPane.add(tokensTF);
		
		JLabel lblTokens = new JLabel("Tokens");
		lblTokens.setBounds(301, 97, 73, 14);
		contentPane.add(lblTokens);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(335, 29, 73, 14);
		contentPane.add(lblPassword);
	}
	
	public void insertEmployee() {
		String fname = fnameTF.getText();
		String lname = lnameTF.getText();
		String phone = phoneTF.getText();
		String cpr = cprTF.getText();
		int res = -1;
		
		EmployeeCtr empCtr = new EmployeeCtr();
		try {
		res = empCtr.insertEmployee(fname, lname, phone, cpr);
		}
		catch(Exception e){
	    	System.out.println(e);
	    }
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Employee successfully created");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe employee was not inserted. \n Please try again");
		}
	}
	
	public void findEmployee() {
		String fname = fnameTF.getText();
		
		EmployeeCtr empCtr = new EmployeeCtr();
		Employee emp = empCtr.findEmployee(fname);
		fnameTF.setText(emp.getFname());
		lnameTF.setText(emp.getLname());
		emailTF.setText(emp.getEmail());
		phoneTF.setText(emp.getPhone());
		cprTF.setText(emp.getCpr());
		passwordTF.setText(emp.getPassword());
		tokensTF.setText(""+emp.getTokens());
		
	}
	
	public void updateEmployee() {
		String fname = fnameTF.getText();
		String lname = lnameTF.getText();
		String email = emailTF.getText();
		String phone = phoneTF.getText();
		String cpr = cprTF.getText();
		String password = passwordTF.getText();
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
	
	public void removeEmployee() {
		String cpr = cprTF.getText();
		int res = -1;
		
		EmployeeCtr empCtr = new EmployeeCtr();
		res = empCtr.removeEmployee(cpr);
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "The employee has been successfully removed");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. The employee has not been removed. \n Please try again");
		}
	}
}
