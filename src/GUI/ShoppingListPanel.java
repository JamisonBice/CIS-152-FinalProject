
package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Classes.ListItem;
import Classes.ShopList;
import Classes.Shopper;

/**
 * @author Jamison Bice
 *
 */
public class ShoppingListPanel extends JPanel {

	private JButton bReturnToMain;// Inits
	private JButton bAddItem;
	private JButton bSort;

	private JTable table;// Table init
	private static ShopList cureentShopList;// Init for the currently in use shop list

	public ShoppingListPanel() {
		setShopList();// Since its static using a method to refresh shoplist
		JPanel form = new JPanel();// init

		form.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));// format
		form.setLayout(new BorderLayout());

		ShopListTable model = new ShopListTable();// Modal Table class is below
		// ShopList currentList =
		table = new JTable(model);
		buttonListener bl = new buttonListener();

		bAddItem = new JButton("Add");// Adds more buttons and listners
		bAddItem.addActionListener(bl);

		bReturnToMain = new JButton("Return to Main");
		bReturnToMain.addActionListener(bl);

		bSort = new JButton("Sort");
		bSort.addActionListener(bl);

		JLabel label = new JLabel(// Label of the shopList
				"List Name: " + cureentShopList.getListName() + "    Date:  " + cureentShopList.getTripDate());

		form.add(label, BorderLayout.PAGE_START);// Adding on the buttons and table with formatting
		form.add(new JScrollPane(table));
		form.add(bAddItem, BorderLayout.LINE_START);
		form.add(bSort, BorderLayout.LINE_END);
		form.add(bReturnToMain, BorderLayout.PAGE_END);
		add(form);
	}

	class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == bAddItem) {// Goes to the New Item Panel to add it to shop list
				removeAll();
				setVisible(false);
				NewListItemPanel newListItemPanel = new NewListItemPanel();
				newListItemPanel.objectPass(cureentShopList);
				add(newListItemPanel);
				validate();
				setVisible(true);

				// newShopList.addItem(newItem);
				// Shopper.addNewList(newShopList);

			} else if (e.getSource() == bReturnToMain) { // Return to main

				removeAll();
				setVisible(false);
				MainMenuPanel newPanel = new MainMenuPanel();
				add(newPanel);
				validate();
				setVisible(true);
			} else if (e.getSource() == bSort) {// Sorts the items from Lowest Price to Highest
				removeAll();
				setVisible(false);
				sortInsert(insertionSort(sort(ShopListTable.getItems())));// Arrays it and sorts it then Un-arrays it
																			// and replants it back in the shoplist
				ShoppingListPanel newPanel = new ShoppingListPanel();// Refresh
				add(newPanel);
				validate();
				setVisible(true);
			}
		}
	}

	public static ListItem[] insertionSort(ListItem[] arr)// got from https://www.geeksforgeeks.org/insertion-sort/ //
															// Modified To use ListItem and Price
	{
		ListItem key = new ListItem(null, null, 0, 0);
		int length = arr.length;
		for (int i = 1; i < length; ++i) {
			key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j].getPrice() > key.getPrice()) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
		return arr;
	}

	public void setShopList() {// Set the current shoplist by peeking from the top of the main stack
		cureentShopList = Shopper.peekList();
	}

	public ListItem[] sort(LinkedList<ListItem> items) {// Puts the items into an array
		ListItem[] arr = new ListItem[ShopListTable.getItems().size()];
		for (int i = 0; i < items.size(); i++) {
			arr[i] = items.get(i);
		}
		return arr;
	}

	public void sortInsert(ListItem[] arr) {// Puts the array items into the linked list
		LinkedList<ListItem> items = ShopListTable.getItems();
		for (int i = 0; i < arr.length; i++) {
			items.set(i, arr[i]);
		}
		ShopListTable.setItems(items);
	}

	public static class ShopListTable extends AbstractTableModel {// Model Table is a custom table with Abstratc Table
																	// modal

		protected static String[] COLUMN_NAMES = { "Item Name ", " Store ", "Price ", "Amount" };// Inits for table
		protected static Class[] COLUMN_CLASSES = { String.class, String.class, Double.class, Integer.class };

		private static LinkedList<ListItem> items;

		public ShopListTable() {
			setItems(cureentShopList.getItems());
		}

		@Override
		public int getRowCount() {// Methods from Abstract Table modle
			return items.size();
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

		public static LinkedList<ListItem> getItems() {// Getter
			return items;
		}

		public static void setItems(LinkedList<ListItem> items) {// Setter
			ShopListTable.items = items;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			ListItem item = items.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return item.getItemName().toString();
			case 1:
				return item.getStore().toString();
			case 2:
				return item.getPrice();
			case 3:
				return item.getAmount();

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

		public void add(ListItem item) {
			int index = items.size();
			items.add(item);
			fireTableRowsInserted(index, index);
		}

	}

}
