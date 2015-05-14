package guiLayer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelLayer.Booking;
import modelLayer.Employee;
import modelLayer.Event;
import modelLayer.TicketType;
import controlLayer.BookingCtr;
import controlLayer.EventCtr;
import controlLayer.TicketTypeCtr;

public class BookingTab extends JPanel {
	private JTextField btTotalPriceTF;
	private JTextField btGuestNameTF;
	private JTextField btPhoneTF;
	private JTextField btEmailTF;
	
	private JComboBox<String> btEvNameCB;
	private JComboBox<String> btTicketTypeCB;
	private JComboBox<String> btTicketQuantCB;
	
	private JComboBox<String> btEmpNameCB;
	private JRadioButton rdbtnCreditCard;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnTokens;
	private JCheckBox chckbxPaid;
	private JLabel lblStatus;

	private int evId = -1;
	private TicketType tt;
	private java.sql.Date bdate;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public BookingTab() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Event information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 350, 170);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSelectEvent = new JLabel("Select event");
		lblSelectEvent.setBounds(10, 30, 100, 14);
		panel.add(lblSelectEvent);
		
		JLabel lblTicketType = new JLabel("Ticket type");
		lblTicketType.setBounds(10, 65, 100, 14);
		panel.add(lblTicketType);
		
		JLabel lblTicketQuantity = new JLabel("Ticket quantity");
		lblTicketQuantity.setBounds(10, 100, 100, 14);
		panel.add(lblTicketQuantity);
		
		btEvNameCB = new JComboBox(HelperClass.getEvents().toArray());
		btEvNameCB.setSelectedIndex(-1);
		btEvNameCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btEvNameCB.getSelectedIndex() != -1) {
					Event ev = (Event)((Wrapper<Event>)btEvNameCB.getSelectedItem()).getObject();
					evId = ev.getEid();
					btTicketTypeCB.setModel(new DefaultComboBoxModel(HelperClass.getTicketTypes(evId).toArray()));
					btTicketTypeCB.setSelectedIndex(-1);
				}
			}
		});
		btEvNameCB.setBounds(120, 27, 200, 20);
		panel.add(btEvNameCB);
		
		btTicketTypeCB = new JComboBox(HelperClass.getTicketTypes(evId).toArray());
		btTicketTypeCB.setSelectedIndex(-1);
		btTicketTypeCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btTicketTypeCB.getSelectedIndex() != -1) {
					tt = ((Wrapper<TicketType>)btTicketTypeCB.getSelectedItem()).getObject();
				}
			}
		});
		btTicketTypeCB.setBounds(120, 62, 200, 20);
		panel.add(btTicketTypeCB);
		
		btTicketQuantCB = new JComboBox<>();
		btTicketQuantCB.setSelectedIndex(-1);
		btTicketQuantCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btTicketQuantCB.getSelectedItem() != null) {
					calculateTotalPrice();
				}
			}
		});
		btTicketQuantCB.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		btTicketQuantCB.setSelectedIndex(-1);
		btTicketQuantCB.setBounds(120, 97, 200, 20);
		panel.add(btTicketQuantCB);
		
		JLabel lblTotalPrice = new JLabel("Total price");
		lblTotalPrice.setBounds(10, 135, 100, 14);
		panel.add(lblTotalPrice);
		
		btTotalPriceTF = new JTextField();
		btTotalPriceTF.setEditable(false);
		btTotalPriceTF.setBounds(120, 132, 200, 20);
		panel.add(btTotalPriceTF);
		btTotalPriceTF.setColumns(10);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		bdate = new java.sql.Date(date.getTime());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Guest information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 192, 564, 130);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGuestName = new JLabel("Guest name");
		lblGuestName.setBounds(10, 30, 100, 14);
		panel_1.add(lblGuestName);
		
		JLabel lblPhoneNumber = new JLabel("Phone number*");
		lblPhoneNumber.setBounds(10, 65, 100, 14);
		panel_1.add(lblPhoneNumber);
		
		JLabel lblEmailAddress = new JLabel("Email address");
		lblEmailAddress.setBounds(10, 100, 100, 14);
		panel_1.add(lblEmailAddress);
		
		btGuestNameTF = new JTextField();
		btGuestNameTF.setBounds(120, 27, 200, 20);
		panel_1.add(btGuestNameTF);
		btGuestNameTF.setColumns(10);
		
		btPhoneTF = new JTextField();
		btPhoneTF.setBounds(120, 62, 200, 20);
		panel_1.add(btPhoneTF);
		btPhoneTF.setColumns(10);
		
		btEmailTF = new JTextField();
		btEmailTF.setBounds(120, 97, 200, 20);
		panel_1.add(btEmailTF);
		btEmailTF.setColumns(10);
		
		JButton btnFindBooking = new JButton("Find booking");
		btnFindBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				findBooking();
			}
		});
		btnFindBooking.setBounds(379, 81, 150, 33);
		panel_1.add(btnFindBooking);
		
		JLabel lblYouCanFind = new JLabel("<html>You can find a booking<br>\r\nby providing a phone number");
		lblYouCanFind.setVerticalAlignment(SwingConstants.TOP);
		lblYouCanFind.setBounds(379, 27, 175, 43);
		panel_1.add(lblYouCanFind);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Payment information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 333, 350, 156);
		add(panel_2);
		panel_2.setLayout(null);
		
		ButtonGroup btngroup = new ButtonGroup();
		
		rdbtnCreditCard = new JRadioButton("Credit Card");
		rdbtnCreditCard.setBounds(6, 30, 109, 23);
		panel_2.add(rdbtnCreditCard);
		
		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(6, 65, 109, 23);
		panel_2.add(rdbtnCash);
		
		rdbtnTokens = new JRadioButton("Tokens");
		rdbtnTokens.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnTokens.isSelected()) {
					btEmpNameCB.setEnabled(true);
				}
				else {
					btEmpNameCB.setEnabled(false);
				}
			}
		});
		rdbtnTokens.setBounds(6, 100, 109, 23);
		panel_2.add(rdbtnTokens);
		
		btngroup.add(rdbtnCreditCard);
		btngroup.add(rdbtnCash);
		btngroup.add(rdbtnTokens);
		
		btEmpNameCB = new JComboBox(HelperClass.getEmployees().toArray());
		btEmpNameCB.setSelectedIndex(-1);
		btEmpNameCB.setBounds(121, 101, 200, 20);
		panel_2.add(btEmpNameCB);
		
		JLabel lblPaymentStatus = new JLabel("Payment status");
		lblPaymentStatus.setBounds(145, 34, 100, 14);
		panel_2.add(lblPaymentStatus);
		
		chckbxPaid = new JCheckBox("Paid");
		chckbxPaid.setBounds(251, 30, 70, 23);
		panel_2.add(chckbxPaid);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(251, 69, 70, 14);
		panel_2.add(lblStatus);
		
		JButton btnNewButton = new JButton("Register booking");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				registerBooking();
			}
		});
		btnNewButton.setBounds(392, 430, 150, 33);
		add(btnNewButton);
		
		JButton btnCancelBooking = new JButton("Cancel booking");
		btnCancelBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cancelBooking();
			}
		});
		btnCancelBooking.setBounds(392, 365, 150, 33);
		add(btnCancelBooking);
		
		textField = new JTextField();
		textField.setText("2015/04/13");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(431, 36, 86, 20);
		add(textField);
		
		JLabel label = new JLabel("Today's date");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(431, 11, 86, 14);
		add(label);
		
		JButton btnNewButton_1 = new JButton("Clear all fields");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearAllFields();
			}
		});
		btnNewButton_1.setBounds(392, 148, 150, 33);
		add(btnNewButton_1);

	}
	
	private void registerBooking() {
		Employee emp = MainWindow.getUser();
		String gname = btGuestNameTF.getText();

		if(HelperClass.nameIsNotValid(gname)){
			JOptionPane.showMessageDialog(null, "Name is not valid. \nThe name should contain only letters");
		}
		else {
			String gphone = btPhoneTF.getText();
			
			if(HelperClass.phoneIsNotValid(gphone)) {
				JOptionPane.showMessageDialog(null, "Phone is not valid. \nThe phone should contain only digits");
			}
			else {
				String gemail = btEmailTF.getText();
	
				if(gemail.trim().length()>0 && !HelperClass.emailIsValid(gemail)) {
					JOptionPane.showMessageDialog(null, "Email is not valid. \nPlease enter a valid email");
				}
				else {
					int quantity = Integer.parseInt(btTicketQuantCB.getSelectedItem().toString());
					
					if(quantity<1 || quantity>10) {
						JOptionPane.showMessageDialog(null, "The specified quantity is invalid");
					}
					else if (quantity>tt.getInStock()) {
						JOptionPane.showMessageDialog(null, "The specified quantity is not available in stock");
					}
					else {
						Double total = Double.parseDouble(btTotalPriceTF.getText());
						int payType = -1;
							if(rdbtnCreditCard.isSelected()){
								payType = 1;
							} else if(rdbtnCash.isSelected()) {
								payType = 2;
							} else if(rdbtnTokens.isSelected()) {
								payType = 3;
							} 
							if (payType == -1) {
								JOptionPane.showMessageDialog(null, "Please select a pay type");
							}
							else {
								String status = "Booked";
									if(chckbxPaid.isSelected()) {
										status = "Paid";
									} 
								int res = -1;
									
								BookingCtr bookCtr = new BookingCtr();
								try {
									res = bookCtr.registerBooking(bdate, emp, tt, quantity, total, gname, gphone, gemail, payType, status);
								}
								catch(Exception e){
							    	System.out.println(e);
							    }
								if(res != -1) {
									JOptionPane.showMessageDialog(null, "Booking successfully registered");
									clearAllFields();
								}
								else {
									JOptionPane.showMessageDialog(null, "An error occured. \nThe booking has not been registered. \n Please try again");
								}
							}
					}
				}
			}
		}
	}
	
	private void findBooking() {
		BookingCtr bookCtr = new BookingCtr();
		String phone = btPhoneTF.getText();
		
		Booking book = bookCtr.findBooking(phone);

		if (book != null ) {
			btEvNameCB.setSelectedItem(book.getTt().getEv().getName());			
			btTicketTypeCB.setSelectedItem(book.getTt().getPrice() + " dkk - " + book.getTt().getName());
			btTicketQuantCB.setSelectedItem("" + book.getQuantity());
			btTotalPriceTF.setText("" + book.getTotal());
			btGuestNameTF.setText(book.getGname());
			btPhoneTF.setText(book.getGphone());
			btEmailTF.setText(book.getGemail());
			switch (book.getPayType()) {
				case 1 : rdbtnCreditCard.setSelected(true);
					break;
				case 2 : rdbtnCash.setSelected(true);
					break;
				case 3 : rdbtnTokens.setSelected(true);
					break; 
			}
			if (book.getStatus().equals("Paid")) {
				chckbxPaid.setSelected(true);
			}
			else if (book.getStatus().equals("Cancelled")) {
				lblStatus.setText("Cancelled");
			}
			else if (book.getStatus().equals("Booked")) {
				lblStatus.setText("Booked");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No booking found with the specified phone number.");
		}
	}
	
	private void cancelBooking() {
		BookingCtr bookCtr = new BookingCtr();
		int res = bookCtr.cancelBooking(btPhoneTF.getText());
		
		if (res != -1) {
			JOptionPane.showMessageDialog(null, "Booking successfully cancelled");
		}
		else {
			JOptionPane.showMessageDialog(null, "An error occured. \nThe booking has not been cancelled. \nPlease try again");
		}
	}
	
	private void calculateTotalPrice() {
		double price = Double.parseDouble(btTicketTypeCB.getSelectedItem().toString().substring(0,5));
		int quantity = Integer.parseInt(btTicketQuantCB.getSelectedItem().toString());
		btTotalPriceTF.setText(""+ (price * quantity));
	}
	
	private void clearAllFields() {
		btEvNameCB.setSelectedIndex(-1);
		btTicketTypeCB.setSelectedIndex(-1);
		btTicketQuantCB.setSelectedIndex(-1);
		btTotalPriceTF.setText("");
		btGuestNameTF.setText("");
		btPhoneTF.setText("");
		btEmailTF.setText("");
		rdbtnCreditCard.setSelected(false);
		rdbtnCash.setSelected(false);
		rdbtnTokens.setSelected(false);
		chckbxPaid.setSelected(false);
		lblStatus.setText("");
	}

}
