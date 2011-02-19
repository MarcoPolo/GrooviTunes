package grooveSharkHandler;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.*;

/**
 * Example of a ToDoList.  Use the Add button to add entries that will be displayed in the list and the table
 */
public class ToDoList extends JFrame {
	private javax.swing.JButton ivjJButton1 = null;
	private JPanel ivjJFrameContentPane = null;
	private JLabel ivjJLabel1 = null;
	private JLabel ivjJLabel11 = null;
	private JTextField ivjJTextField1 = null;
	private JScrollPane ivjJScrollPane = null;
	private JTable ivjJTable = null;
	private TableColumn ivjTableColumn = null;
	private TableColumn ivjTableColumn2 = null;
	private int taskCount = 0;
	private JTabbedPane ivjJTabbedPane = null;
	private JScrollPane ivjJScrollPane2 = null;
	private JList ivjJList = null;

	public ToDoList() {
		super();
		initialize();
	}

	/**
	 * Return the JButton1 property value.
	 * @return JButton
	 */
	private JButton getJButton1() {
		if (ivjJButton1 == null) {
			ivjJButton1 = new JButton();
			ivjJButton1.setName("JButton1");
			ivjJButton1.setText("Add Item");
			ivjJButton1.setBounds(190, 51, 131, 25);
			ivjJButton1.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent arg0) {
					String newTask = getJTextField1().getText();
					if (!newTask.equals("")) {
						DefaultListModel listModel = (DefaultListModel) getIvjJList()
								.getModel();
						listModel.addElement(newTask);
						TableModel model = getIvjJTable().getModel();
						model.setValueAt(newTask, taskCount, 0);
						model.setValueAt(new GregorianCalendar().getTime()
								.toString(), taskCount, 1);
						getJTextField1().setText("");
						if (taskCount < model.getRowCount() - 1)
							taskCount++;
					}
				}
			});
		}
		return ivjJButton1;
	}

	/**
	 * Return the JFrameContentPane property value.
	 * @return JPanel
	 */
	private JPanel getJFrameContentPane() {
		if (ivjJFrameContentPane == null) {
			ivjJFrameContentPane = new JPanel();
			ivjJFrameContentPane.setName("JFrameContentPane");
			ivjJFrameContentPane.setLayout(null);
			getJFrameContentPane().add(getJLabel1(), getJLabel1().getName());
			getJFrameContentPane().add(getJTextField1(),
					getJTextField1().getName());
			getJFrameContentPane().add(getJButton1(), getJButton1().getName());
			getJFrameContentPane().add(getJLabel11(), getJLabel11().getName());
			ivjJFrameContentPane.add(getIvjJTabbedPane(), getIvjJTabbedPane()
					.getName()); // JVE Generated
		}
		return ivjJFrameContentPane;
	}

	/**
	 * Return the JLabel1 property value.
	 * @return JLabel
	 */
	private JLabel getJLabel1() {
		if (ivjJLabel1 == null) {
			ivjJLabel1 = new JLabel();
			ivjJLabel1.setName("JLabel1");
			ivjJLabel1.setText("To-Do Item");
			ivjJLabel1.setBounds(10, 33, 98, 15);
		}
		return ivjJLabel1;
	}

	/**
	 * Return the JLabel11 property value.
	 * @return JLabel
	 */
	private JLabel getJLabel11() {
		if (ivjJLabel11 == null) {
			ivjJLabel11 = new JLabel();
			ivjJLabel11.setName("JLabel11");
			ivjJLabel11.setText("To-Do List");
			ivjJLabel11.setBounds(10, 104, 98, 15);
		}
		return ivjJLabel11;
	}

	/**
	 * Return the JTextField1 property value.
	 * @return JTextField
	 */
	private JTextField getJTextField1() {
		if (ivjJTextField1 == null) {
			ivjJTextField1 = new JTextField();
			ivjJTextField1.setName("JTextField1");
			ivjJTextField1.setBounds(10, 57, 140, 19);
			ivjJTextField1.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent arg0) {
					String newTask = getJTextField1().getText();
					if (!newTask.equals("")) {
						DefaultListModel listModel = (DefaultListModel) getIvjJList()
								.getModel();
						listModel.addElement(newTask);
						TableModel model = getIvjJTable().getModel();
						model.setValueAt(newTask, taskCount, 0);
						model.setValueAt(new GregorianCalendar().getTime()
								.toString(), taskCount, 1);
						getJTextField1().setText("");
						if (taskCount < model.getRowCount() - 1)
							taskCount++;
					}
				}
			});
		}
		return ivjJTextField1;
	}

	/**
	 * Initialize the class.
	 */
	private void initialize() {
		setName("JFrame1");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(360, 320);
		setTitle("To-Do List  Swing Controls");
		setContentPane(getJFrameContentPane());

	}

	/**
	 * This method initializes ivjJScrollPane
	 * 
	 * @return JScrollPane
	 */
	private JScrollPane getIvjJScrollPane() {
		if (ivjJScrollPane == null) {
			ivjJScrollPane = new JScrollPane(); // Explicit Instance
			ivjJScrollPane.setViewportView(getIvjJTable()); // JVE Generated
			ivjJScrollPane.setBounds(10, 130, 329, 146); // JVE Generated
		}
		return ivjJScrollPane;
	}

	/**
	 * Static inner class for the table model
	 */
	public static class ToDoListModel extends AbstractTableModel {

		String[][] data = { { "", "" }, { "", "" }, { "", "" }, { "", "" },
				{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
				{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
				{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
				{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
				{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
				{ "", "" }, { "", "" }, { "", "" } };

		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}

		public int getColumnCount() {
			return 0;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int c) {
			return ("");
		}

		public void setValueAt(Object arg0, int arg1, int arg2) {
			data[arg1][arg2] = (String) arg0;
			fireTableDataChanged();
		}
	}

	/**
	 * This method initializes ivjJTable
	 * 
	 * @return JTable
	 */
	private JTable getIvjJTable() {
		if (ivjJTable == null) {
			ivjJTable = new JTable(); // Explicit Instance
			ivjJTable.addColumn(getIvjTableColumn()); // JVE Generated
			ivjJTable.addColumn(getIvjTableColumn2()); // JVE Generated
			ivjJTable.setAutoCreateColumnsFromModel(false); // JVE Generated
			ivjJTable.setModel(new ToDoListModel());
		}
		return ivjJTable;
	}

	/**
	 * This method initializes ivjTableColumn
	 * 
	 * @return table.TableColumn
	 */
	private TableColumn getIvjTableColumn() {
		if (ivjTableColumn == null) {
			ivjTableColumn = new TableColumn(); // Explicit Instance
			ivjTableColumn.setHeaderValue("Task");
			ivjTableColumn.setPreferredWidth(40); // JVE Generated
			ivjTableColumn.setResizable(false); // JVE Generated
			ivjTableColumn.setModelIndex(0); // JVE Generated
		}
		return ivjTableColumn;
	}

	/**
	 * This method initializes ivjTableColumn2
	 * 
	 * @return table.TableColumn
	 */
	private TableColumn getIvjTableColumn2() {
		if (ivjTableColumn2 == null) {
			ivjTableColumn2 = new TableColumn(); // Explicit Instance
			ivjTableColumn2.setHeaderValue("Time Added");
			ivjTableColumn2.setPreferredWidth(90); // JVE Generated
			ivjTableColumn2.setModelIndex(1); // JVE Generated
		}
		return ivjTableColumn2;
	}

	/**
	 * This method initializes ivjJTabbedPane
	 * 
	 * @return JTabbedPane
	 */
	private JTabbedPane getIvjJTabbedPane() {
		if (ivjJTabbedPane == null) {
			ivjJTabbedPane = new JTabbedPane(); // Explicit Instance
			ivjJTabbedPane
					.addTab("Task List", null, getIvjJScrollPane2(), null); // JVE Generated
			ivjJTabbedPane.addTab("Details", null, getIvjJScrollPane(), null); // JVE Generated
			ivjJTabbedPane.setBounds(5, 120, 335, 165); // JVE Generated
		}
		return ivjJTabbedPane;
	}

	/**
	 * This method initializes ivjJScrollPane2
	 * 
	 * @return JScrollPane
	 */
	private JScrollPane getIvjJScrollPane2() {
		if (ivjJScrollPane2 == null) {
			ivjJScrollPane2 = new JScrollPane(); // Explicit Instance
			ivjJScrollPane2.setViewportView(getIvjJList()); // JVE Generated
		}
		return ivjJScrollPane2;
	}

	/**
	 * This method initializes ivjJList
	 * 
	 * @return JList
	 */
	private JList getIvjJList() {
		if (ivjJList == null) {
			ivjJList = new JList(); // Explicit Instance
			ivjJList.setModel(new DefaultListModel());
		}
		return ivjJList;
	}
}
