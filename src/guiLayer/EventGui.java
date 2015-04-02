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

import modelLayer.Event;
import controlLayer.EventCtr;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventGui extends JFrame {

	private JPanel contentPane;
	private JTextField nameTF;
	private JTextField descTF;
	private JTextField eidTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventGui frame = new EventGui();
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
	public EventGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(12, 13, 494, 425);
		contentPane.add(panel);
		
		JLabel eventName = new JLabel("Event Name");
		eventName.setBounds(212, 48, 81, 14);
		panel.add(eventName);
		
		JLabel eventDesc = new JLabel("Event Description");
		eventDesc.setBounds(199, 119, 109, 14);
		panel.add(eventDesc);
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(171, 78, 169, 20);
		panel.add(nameTF);
		
		descTF = new JTextField();
		descTF.setColumns(10);
		descTF.setBounds(96, 146, 322, 126);
		panel.add(descTF);
		
		JButton button = new JButton("Insert");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				insertEvent();
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(10, 389, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Find");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				findEvent();
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(151, 389, 89, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Update");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateEvent();
			}
		});
		button_2.setBounds(274, 389, 89, 23);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Remove");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeEvent();
			}
		});
		button_3.setBounds(405, 389, 89, 23);
		panel.add(button_3);
		
		eidTF = new JTextField();
		eidTF.setColumns(10);
		eidTF.setBounds(359, 335, 123, 20);
		panel.add(eidTF);
		
		JLabel eventId = new JLabel("Event ID");
		eventId.setBounds(394, 308, 73, 14);
		panel.add(eventId);
	}
	
	public void insertEvent() {
		String name = nameTF.getText();
		String description = descTF.getText();
		
		int res = -1;
		
		EventCtr evCtr = new EventCtr();
		try {
		res = evCtr.insertEvent(name, description);
		}
		catch(Exception e){
	    	System.out.println(e);
	    }
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "Event successfully created");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe event was not inserted. \n Please try again");
		}
	}
	
	public void findEvent() {
		String name = nameTF.getText();
		
		EventCtr evCtr = new EventCtr();
		Event ev = new Event();
		ev = evCtr.findEvent(name);
		if(ev != null) {
			nameTF.setText(ev.getName());
			descTF.setText(ev.getDescription());
			eidTF.setText(""+ev.getEid());
			eidTF.setEditable(false);
			
		}
		else {
			System.out.println("Event empty");
		}
	}
	public void updateEvent() {
		String name = nameTF.getText();
		String description = descTF.getText();
		int eid = Integer.parseInt(eidTF.getText());
		
		int res = -1;
		
		EventCtr evCtr = new EventCtr();
		res = evCtr.updateEvent(name, description, eid);
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "The information has been successfully updated");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. The information has not been updated. \n Please try again");
		}
	}
	
	public void removeEvent() {
		int eid = Integer.parseInt(eidTF.getText());
		int res = -1;
		
		EventCtr evCtr = new EventCtr();
		res = evCtr.removeEvent(eid);
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "The event has been successfully removed");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. The event has not been removed. \n Please try again");
		}
	}
}


