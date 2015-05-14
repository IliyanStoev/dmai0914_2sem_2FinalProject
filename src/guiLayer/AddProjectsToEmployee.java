package guiLayer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelLayer.Employee;
import modelLayer.Event;
import controlLayer.EventCtr;

public class AddProjectsToEmployee extends JFrame {

	private JPanel contentPane;
	private Event ev1;
	private Event ev2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProjectsToEmployee frame = new AddProjectsToEmployee();
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
	public AddProjectsToEmployee() {
		setTitle("Employee-Projects");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Select projects");
		label.setBounds(10, 10, 100, 20);
		contentPane.add(label);
		
		JComboBox proj1CB = new JComboBox(HelperClass.getEvents().toArray());
		proj1CB.setSelectedIndex(-1);
		proj1CB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(proj1CB.getSelectedIndex() != -1) {
					ev1 = (Event)((Wrapper<Event>)proj1CB.getSelectedItem()).getObject();
				}
			}
		});
		proj1CB.setBounds(100, 10, 200, 20);
		contentPane.add(proj1CB);
		
		JComboBox proj2CB = new JComboBox(HelperClass.getEvents().toArray());
		proj2CB.setSelectedIndex(-1);
		proj2CB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(proj2CB.getSelectedIndex() != -1) {
					ev2 = (Event)((Wrapper<Event>)proj2CB.getSelectedItem()).getObject();
				}
			}
		});
		proj2CB.setBounds(100, 49, 200, 20);
		contentPane.add(proj2CB);
		
		JButton button = new JButton("Add");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addProjects();
			}
		});
		button.setBounds(100, 94, 141, 31);
		contentPane.add(button);
	}
	
	private void addProjects() {
		EventCtr evCtr = new EventCtr();
		Employee emp = MainWindow.getUser();
		int res = -1;
		
		if(ev1 != null) {
			if (!evCtr.isDuplicate(ev1, emp))
				try {
					res = evCtr.insertEmpEvent(ev1, emp);
				} catch(Exception e) {
					System.out.println(e);
				}
			else {
				JOptionPane.showMessageDialog(null, "The first project is already associated with the specified employee");
			}
		}
		
		if(res != -1 && ev2 != null) {
			if (!evCtr.isDuplicate(ev2, emp)) {
				try {
					res = evCtr.insertEmpEvent(ev2, emp);
				} catch(Exception e) {
					System.out.println(e);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "The second project is already associated with the specified employee");
			}
		}
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Project/s added.");
		}
		
		this.dispose();
	}

}
