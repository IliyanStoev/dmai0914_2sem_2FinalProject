package guiLayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modelLayer.Event;
import controlLayer.EventCtr;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventOverview extends JPanel {
	private JTextField eoDateTF;
	private JTextField eoEvIdTF;
	private JTextField eoDescriptionTF;
	private JTextField eoBudgetTF;
	private JComboBox<String> eoEvNameCB;
	private JComboBox<String> eoProjManCB;

	/**
	 * Create the panel.
	 */
	public EventOverview() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Event information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 565, 478);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 30, 100, 14);
		panel.add(lblName);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 65, 100, 14);
		panel.add(lblDate);
		
		JLabel lblProjectManager = new JLabel("Project manager");
		lblProjectManager.setBounds(10, 135, 100, 14);
		panel.add(lblProjectManager);
		
		eoEvNameCB = new JComboBox(HelperClass.getEvents().toArray());
		eoEvNameCB.setSelectedIndex(-1);
		eoEvNameCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillEventInfo();
			}
		});
		eoEvNameCB.setBounds(120, 27, 180, 20);
		panel.add(eoEvNameCB);
		
		eoDateTF = new JTextField();
		eoDateTF.setEditable(false);
		eoDateTF.setBounds(120, 62, 180, 20);
		panel.add(eoDateTF);
		eoDateTF.setColumns(10);
		
		eoProjManCB = new JComboBox(HelperClass.getEmployees().toArray());
		eoProjManCB.setSelectedIndex(-1);
		eoProjManCB.setBounds(120, 132, 180, 20);
		panel.add(eoProjManCB);
		
		JLabel lblEventId = new JLabel("Event id");
		lblEventId.setBounds(380, 30, 100, 14);
		panel.add(lblEventId);
		
		eoEvIdTF = new JTextField();
		eoEvIdTF.setEditable(false);
		eoEvIdTF.setBounds(490, 27, 65, 20);
		panel.add(eoEvIdTF);
		eoEvIdTF.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 290, 100, 14);
		panel.add(lblDescription);
		
		eoDescriptionTF = new JTextField();
		eoDescriptionTF.setEditable(false);
		eoDescriptionTF.setBounds(120, 290, 435, 130);
		panel.add(eoDescriptionTF);
		eoDescriptionTF.setColumns(10);
		
		JLabel lblBudget = new JLabel("Budget");
		lblBudget.setBounds(10, 100, 100, 14);
		panel.add(lblBudget);
		
		eoBudgetTF = new JTextField();
		eoBudgetTF.setEditable(false);
		eoBudgetTF.setBounds(120, 97, 180, 20);
		panel.add(eoBudgetTF);
		eoBudgetTF.setColumns(10);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setBounds(10, 170, 100, 14);
		panel.add(lblTeam);
		
		JList eoTeamList = new JList();
		eoTeamList.setEnabled(false);
		eoTeamList.setBounds(120, 169, 280, 100);
		panel.add(eoTeamList);
		
		JButton btnEditInformation = new JButton("Edit information");
		btnEditInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				edit();
			}
		});
		btnEditInformation.setBounds(295, 444, 125, 23);
		panel.add(btnEditInformation);
		
		JButton btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateEvent();
			}
		});
		btnSaveChanges.setBounds(430, 444, 125, 23);
		panel.add(btnSaveChanges);

	}
	
	private void fillEventInfo() {
		EventCtr evCtr = new EventCtr();
		String evName = eoEvNameCB.getSelectedItem().toString();
		Event ev = evCtr.findEvent(evName);
		
		if(ev != null) {
			eoDateTF.setText(""+ev.getDate());
			eoEvIdTF.setText(""+ev.getEid());
			eoBudgetTF.setText(""+ev.getBudget());
			eoProjManCB.setSelectedItem(ev.getProjectManager().getFname());
			eoDescriptionTF.setText(ev.getDescription());
		}
	}
	
	private void updateEvent() {
		String evName = eoEvNameCB.getSelectedItem().toString();
		double budget = Double.parseDouble(eoBudgetTF.getText());
		String description = eoDescriptionTF.getText();
		int eid = Integer.parseInt(eoEvIdTF.getText());
		
		EventCtr evCtr = new EventCtr();
		int res = evCtr.updateEvent(evName, budget, description, eid);
		
		if(res != -1) {
			JOptionPane.showMessageDialog(null, "The information has been successufully updated");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe information has not been updated. \nPlease try again.");
		}
	}
	
	private void edit() {
		eoEvNameCB.setEditable(true);
		//eoDateTF.setEditable(true);
		eoBudgetTF.setEditable(true);
		eoDescriptionTF.setEditable(true);
	}
	
}
