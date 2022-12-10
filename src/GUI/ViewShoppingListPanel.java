package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Classes.ShopList;
import Classes.Shopper;

/**
 * @author Jamison Bice
 *
 */
public class ViewShoppingListPanel extends JPanel {

	private JButton bReturnToMain;// Inits
	private JButton bAddItem;
	private JButton bUpdate;
	private static Stack<ShopList> shoppingLists;// Stack of the Lists
	private JTable table;

	public ViewShoppingListPanel() {
		setShopper();// Refresher
		JPanel form = new JPanel();

		form.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));// Format
		form.setLayout(new BorderLayout());

		ShoppingListTable model = new ShoppingListTable();// Tabel modle similair to the Shopping list one
		// ShopList currentList =
		table = new JTable(model);
		buttonListener bl = new buttonListener();

		bAddItem = new JButton("Add");// Buttons and Listeners
		bAddItem.addActionListener(bl);

		bReturnToMain = new JButton("Return to Main");
		bReturnToMain.addActionListener(bl);

		bUpdate = new JButton("Update");
		bUpdate.addActionListener(bl);

		JLabel label = new JLabel("All Present Shopping Lists");// Single Label
		form.add(label, BorderLayout.PAGE_START);// Buttons and format
		form.add(new JScrollPane(table));
		form.add(bAddItem, BorderLayout.LINE_START);
		form.add(bReturnToMain, BorderLayout.PAGE_END);
		add(form);
	}

	class buttonListener implements ActionListener {// Listener for button presses

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == bAddItem) {// TO Create new Shopping list
				removeAll();
				setVisible(false);
				NewShopListPanel newShopListPanel = new NewShopListPanel();
				add(newShopListPanel);
				validate();
				setVisible(true);

				// newShopList.addItem(newItem);
				// Shopper.addNewList(newShopList);

			} else if (e.getSource() == bReturnToMain) {// Return to main

				removeAll();
				setVisible(false);
				MainMenuPanel newPanel = new MainMenuPanel();
				add(newPanel);
				validate();
				setVisible(true);
			}

		}
	}

	public void setShopper() {// Setter cause Static
		shoppingLists = Shopper.getShoppingLists();
	}

	public static class ShoppingListTable extends AbstractTableModel { // Similiar table to Shopping List except less
																		// columns and no sort

		protected static String[] COLUMN_NAMES = { "List Name ", " Date " };
		protected static Class[] COLUMN_CLASSES = { String.class, Date.class };

		private Stack<ShopList> shopList;

		public ShoppingListTable() {
			shopList = Shopper.getShoppingLists();
		}

		@Override
		public int getRowCount() {
			return shopList.size();
		}

		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override
		public String getColumnName(int column) {
			return COLUMN_NAMES[column];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return COLUMN_CLASSES[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			ShopList item = shopList.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return item.getListName().toString();
			case 1:
				return item.getTripDate();

			}
			return null;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (columnIndex != 4) {
				return;
			}
			if (!(aValue instanceof Boolean)) {
				return;
			}

			fireTableCellUpdated(rowIndex, columnIndex);
		}

		public void add(ShopList item) {
			int index = shopList.size();
			shopList.add(item);
			fireTableRowsInserted(index, index);
		}

	}
}
