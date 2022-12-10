package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import Classes.ListItem;
import Classes.ShopList;
import Classes.Shopper;

/**
 * @author Jamison Bice
 *
 */
public class NewShopListPanel extends JPanel {

	private JTextField tListName;// Componenet Inits
	private JTextField tItemName;
	private JTextField tPrice;
	private JTextField tAmount;
	private Date enteredDate;
	private JComboBox<String> comboBoxStore;
	private JButton bSubmitNewList;
	private JButton bClearEntry;
	private JButton bReturnToMain;
	private ShopList newShopList;

	/**
	 * Create the panel.
	 */
	public NewShopListPanel() {

		JPanel form = new JPanel();

		form.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));// Format
		form.setLayout(new GridLayout(0, 2));

		JLabel lListName = new JLabel("ListName:");// Label name
		form.add(lListName, "4, 4, right, default");

		tListName = new JTextField();// Text Field Name
		form.add(tListName, "8, 4, fill, default");
		tListName.setColumns(10);

		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");// Non-input Date created
		DateFormatter df = new DateFormatter(format);
		JFormattedTextField tDate = new JFormattedTextField(df);
		tDate.setValue(new Date());
		enteredDate = (Date) tDate.getValue();
		// tDate.setFocusLostBehavior(JFormattedTextField.COMMIT);Only for input when
		// they deselect

		JLabel lItemName = new JLabel("Item Name:");// Item Name Label These are all the same as the List Item just here
													// to crerate the shopping list with at least on Item
		form.add(lItemName, "4, 10, right, default");

		tItemName = new JTextField();
		form.add(tItemName, "8, 10, fill, default");
		tItemName.setColumns(10);

		JLabel lStore = new JLabel("Store:");
		form.add(lStore, "4, 10, right, default");

		comboBoxStore = new JComboBox<String>();
		comboBoxStore.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Target", "Walmart", "HyVee", "Sam's Club", "Costco" }));
		form.add(comboBoxStore, "8, 8, fill, default");

		JLabel lPrice = new JLabel("Price:");
		form.add(lPrice, "4, 10, right, default");

		tPrice = new JTextField();
		form.add(tPrice, "8, 10, fill, integer");
		tPrice.setColumns(10);

		JLabel lAmount = new JLabel("Amount:");
		form.add(lAmount, "4, 12, right, default");

		tAmount = new JTextField();
		form.add(tAmount, "8, 12, fill, default");
		tAmount.setColumns(10);

		buttonListener bl = new buttonListener();

		bSubmitNewList = new JButton("Submit New List");// Submit button
		bSubmitNewList.addActionListener(bl);
		form.add(bSubmitNewList, "8, 14");

		bClearEntry = new JButton("Clear Entry");// Clear button
		bClearEntry.addActionListener(bl);
		form.add(bClearEntry, "8, 16");

		bReturnToMain = new JButton("Return to Main");// Return button
		bReturnToMain.addActionListener(bl);
		form.add(bReturnToMain, "8, 18");

		add(form);

	}

	class buttonListener implements ActionListener {// Listener for button presses

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == bSubmitNewList) {// Submit creates the item like before but also creates a shoplist
													// with the new item and adds the shoplist to the shopper stack
				try {
					ListItem newItem = new ListItem(tItemName.getText(), (String) comboBoxStore.getSelectedItem(),
							Double.parseDouble(tPrice.getText()), Integer.parseInt(tAmount.getText()));
					newShopList = new ShopList(tListName.getText(), enteredDate);
					newShopList.addItem(newItem);
					Shopper.addNewList(newShopList);

					clearAllFields();
				} catch (NumberFormatException | NullPointerException ex) {// error catch
					JOptionPane.showMessageDialog(null, "Please input fields", "Warning", JOptionPane.WARNING_MESSAGE);

				}

			} else if (e.getSource() == bClearEntry) {// Same as New List Item

				clearAllFields();

			} else if (e.getSource() == bReturnToMain) {// Same as New List Item

				removeAll();
				setVisible(false);
				MainMenuPanel newPanel = new MainMenuPanel();
				add(newPanel);
				validate();
				setVisible(true);
			}

		}

		// public void SendObject() {
		// newShopList
		// }

		private void clearAllFields() {// Set to defaults
			tListName.setText("");
			comboBoxStore.setSelectedIndex(0);
			tItemName.setText("");
			tPrice.setText("");
			tAmount.setText("");

		}
	}
}
