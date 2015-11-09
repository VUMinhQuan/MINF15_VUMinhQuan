package heapsort;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Main extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	public static String[] colNames = { "Title", "TimeSpan", "Priority" };
	public static ArrayList<EmailClass> listEmail = new ArrayList<>();
	public static int rowSelected;
	public int colSelected;
	public static EmailClass emailItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Manage Email");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Add Action to Add Button
				EmailItem inputButton = new EmailItem(false);
				inputButton.setVisible(true);
			}
		});

		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailItem item = new EmailItem(true);
				if (emailItem != null) {
					item.txtEmail.setText(emailItem.getTitle());
					item.cbBox.setSelectedItem(emailItem.getColorText());
					item.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Please select email to edit.");
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Add Action to Delete Button
				if(listEmail.size()>0){
					listEmail.remove(0);
	
				

					// apply heap sort
					HeapSort.Sort(listEmail, listEmail.size());

					ReloadDataForGrid();
				} else {
					JOptionPane.showMessageDialog(null,
							"Your List email is Empty");
				}
			}
		});

		JButton btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add Action to Clear Button
				DeleteAllEmail(true);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																scrollPane,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																414,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.LEADING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				btnAdd,
																				GroupLayout.DEFAULT_SIZE,
																				74,
																				Short.MAX_VALUE)
																		.addGap(39)
																		.addComponent(
																				btnEdit,
																				GroupLayout.DEFAULT_SIZE,
																				75,
																				Short.MAX_VALUE)
																		.addGap(32)
																		.addComponent(
																				btnDelete,
																				GroupLayout.DEFAULT_SIZE,
																				76,
																				Short.MAX_VALUE)
																		.addGap(41)
																		.addComponent(
																				btnDeleteAll,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(19)
										.addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 193,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnAdd,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnEdit,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnDelete,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnDeleteAll,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));

		table = new JTable();

		Init();

		table.setModel(new DefaultTableModel(new Object[][] {}, colNames));

		ReloadDataForGrid();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					rowSelected = table.rowAtPoint(e.getPoint());// get
					colSelected = table.columnAtPoint(e.getPoint());// get

					String title = table.getValueAt(rowSelected, 0).toString();
					String timespan = table.getValueAt(rowSelected, 1)
							.toString();
					String color = table.getValueAt(rowSelected, 2).toString();
					emailItem = new EmailClass(title, new Date(timespan), color);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.toString());
				}

			}
		});

		contentPane.setLayout(gl_contentPane);
	}

	// Init
	private void Init() {
		listEmail = new ArrayList<EmailClass>();
	}

	// Create DataSource
	public static void ReloadDataForGrid() {
		int indexRow = 0;

		DeleteAllEmail(false);
		for (EmailClass email : listEmail) {
			Object[] recordEmail = new Object[] { email.getTitle(),
					email.getTimeSpanWithFormat(), email.getColorText() };

			((DefaultTableModel) table.getModel()).insertRow(indexRow,
					recordEmail);
			
			indexRow++;
		}
	}

	// Delete all email
	public static void DeleteAllEmail(boolean forceInit) {
		if (forceInit) {
			listEmail = new ArrayList<EmailClass>();
		}

		table.setModel(new DefaultTableModel(new Object[][] {}, colNames));
	}
}
