package heapsort;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.Window.Type;

import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.Choice;
import java.awt.Panel;
import java.awt.ScrollPane;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EmailItem extends JFrame {

	private JPanel contentPane;
	JTextField txtEmail;
	public boolean isEditMode;
	public JComboBox cbBox = new JComboBox();	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailItem frame = new EmailItem(true);
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
	public EmailItem(boolean editMode) {
		isEditMode = editMode;
		setTitle(editMode ? "Edit Email" : "Add Email");
		setBounds(100, 100, 443, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel(" Email Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Priority");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save
				saveEmail(isEditMode);
				setVisible(false);
			}
		});

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close
				setVisible(false);
			}
		});

		cbBox.setModel(new DefaultComboBoxModel(new String[] {
				"[Please Select]", "RED", "GREEN", "BLUE", "YELLOW", "WHITE",
				"BLACK" }));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(195)
																		.addComponent(
																				btnSave,
																				GroupLayout.PREFERRED_SIZE,
																				89,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(10)
																		.addComponent(
																				btnClose,
																				GroupLayout.PREFERRED_SIZE,
																				89,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addGap(39)
																										.addComponent(
																												lblNewLabel))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addGap(59)
																										.addComponent(
																												lblNewLabel_1)))
																		.addGap(24)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								cbBox,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtEmail,
																								GroupLayout.PREFERRED_SIZE,
																								259,
																								GroupLayout.PREFERRED_SIZE))))
										.addGap(30)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(11)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblNewLabel,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				txtEmail,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel_1)
														.addComponent(
																cbBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(34)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(btnSave)
														.addComponent(btnClose))));
		contentPane.setLayout(gl_contentPane);
	}

	// Add new Email when click button
	private void saveEmail(boolean isEditMode) {
		try {
			if (txtEmail.getText().equals("")) {
				// Show error
				JOptionPane.showMessageDialog(null, "Please input Email Title");
			} else {
				if (isEditMode){				
					for (Iterator<EmailClass> it = Main.listEmail.iterator(); it
							.hasNext();) {
						EmailClass nextEmail = it.next();
						if (nextEmail.getTitle().equals(Main.emailItem.getTitle())
								&& nextEmail.getTimeSpanWithFormat().equals(
										Main.emailItem.getTimeSpanWithFormat())) {
							it.remove();
						}
					}		
				} 
				
				Main.listEmail.add(new EmailClass(txtEmail.getText(),
						(String) cbBox.getSelectedItem().toString().trim()));

				// heap sort
				HeapSort.Sort(Main.listEmail, Main.listEmail.size());

				// stock in array			
				Main.ReloadDataForGrid();
			}
		} catch (Exception e) {

		}
	}

	private int getColorByText(String text) {
		int result = 6;

		switch (text) {
		case "RED":
			result = 0;
			break;
		case "GREEN":
			result = 1;
			break;
		case "BLUE":
			result = 2;
			break;
		case "YELLOW":
			result = 3;
			break;
		case "WHITE":
			result = 4;
			break;
		case "BLACK":
			result = 5;
			break;
		case "UNCATEGORY":
			result = 6;
			break;

		default:
			result = 6;
			break;
		}

		return result;
	}
}
