package guiLayer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import modelLayer.Employee;
import modelLayer.Event;
import modelLayer.TicketType;
import controlLayer.EmployeeCtr;
import controlLayer.EventCtr;
import controlLayer.TicketTypeCtr;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private UserInformation uInf = new UserInformation();
	private ManagerTab mngTab = new ManagerTab();
	private EventOverview evOv = new EventOverview();
	private BookingTab bt = new BookingTab();
	
	private static JComboBox users;
	private final JLabel lblFindEmployee = new JLabel("Logged employee");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 30, 584, 520);
		contentPane.add(tabbedPane);
		
		tabbedPane.add("User Tab", uInf);
		tabbedPane.add("Manager Tab", mngTab);
		tabbedPane.add("Event overview", evOv);
		tabbedPane.add("BookingTab", bt);
		
		users = new JComboBox<>(HelperClass.getEmployees().toArray());
		users.setSelectedIndex(-1);
		users.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = users.getSelectedItem().toString();
				uInf.fillUserInfo(user);
			}
		});
		users.setBounds(444, 11, 150, 20);
		
		contentPane.add(users);
		lblFindEmployee.setBounds(310, 14, 110, 14);
		
		contentPane.add(lblFindEmployee);
	}
	
	public static Employee getUser() {
		EmployeeCtr empCtr = new EmployeeCtr();
		String userName = users.getSelectedItem().toString();
		Employee user = empCtr.findEmployee(userName);
		return user;
	}
}
