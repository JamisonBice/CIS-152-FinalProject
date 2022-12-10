package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.ListItem;
import Classes.ShopList;

/**
 * @author Jamison Bice
 *
 */
public class NewListItemPanel extends JPanel {

	private JTextField tItemName;// Inits of all componenets
	private JTextField tPrice;
	private JTextField tAmount;
	private JComboBox<String> comboBoxStore;
	private JButton bSubmitNewList;
	private JButton bClearEntry;
	private JButton bReturnToMain;
	private ShopList newShopList;

	/**
	 * Create the panel.
	 */
	public NewListItemPanel() {

		JPanel form = new JPanel();// inits

		form.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));// Format
		form.setLayout(new GridLayout(0, 2));

		JLabel lItemName = new JLabel("Item Name:");// Label for name
		form.add(lItemName, "4, 10, right, default");

		tItemName = new JTextField();// Text field for name
		form.add(tItemName, "8, 10, fill, default");
		tItemName.setColumns(10);

		JLabel lStore = new JLabel("Store:");
		form.add(lStore, "4, 10, right, default");// Label for store

		comboBoxStore = new JComboBox<String>();// Drop down box for the many stores
		comboBoxStore.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Target", "Walmart", "HyVee", "Sam's Club", "Costco" }));
		form.add(comboBoxStore, "8, 8, fill, default");

		JLabel lPrice = new JLabel("Price:");// Label for price
		form.add(lPrice, "4, 10, right, default");

		tPrice = new JTextField();// Text field for price
		form.add(tPrice, "8, 10, fill, integer");
		tPrice.setColumns(10);

		JLabel lAmount = new JLabel("Amount:");// Amount label
		form.add(lAmount, "4, 12, right, default");

		tAmount = new JTextField();// Amount Text field
		form.add(tAmount, "8, 12, fill, default");
		tAmount.setColumns(10);

		buttonListener bl = new buttonListener(); // Lisetnor for presses

		bSubmitNewList = new JButton("Submit New Item");// Submit button
		bSubmitNewList.addActionListener(bl);
		form.add(bSubmitNewList, "8, 14");

		bClearEntry = new JButton("Clear Entry");// Clear button
		bClearEntry.addActionListener(bl);
		form.add(bClearEntry, "8, 16");

		bReturnToMain = new JButton("Return to Main");// Return to main
		bReturnToMain.addActionListener(bl);
		form.add(bReturnToMain, "8, 18");

		add(form);

	}

	class buttonListener implements ActionListener {// Listener

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bSubmitNewList) {//Submit 
				try {
					ListItem newItem = new ListItem(tItemName.getText(), (String) comboBoxStore.getSelectedItem(),// Gets all thje text and makes a new List Item
							Double.parseDouble(tPrice.getText()), Integer.parseInt(tAmount.getText()));

					newShopList.addItem(newItem);

					clearAllFields();
				} catch (NumberFormatException | NullPointerException ex) {//Error Catch
					JOptionPane.showMessageDialog(null, "No Shop List", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			} else if (e.getSource() == bClearEntry) {//Clear the entries

				clearAllFields();

			} else if (e.getSource() == bReturnToMain) {//Just removes the panel and creates the mani panel again
				removeAll();
				setVisible(false);
				MainMenuPanel newPanel = new MainMenuPanel();
				add(newPanel);
				validate();
				setVisible(true);
			}

		}

		private void clearAllFields() {//Sets them all to "Default"
			comboBoxStore.setSelectedIndex(0);
			tItemName.setText("");
			tPrice.setText("");
			tAmount.setText("");

		}
	}

	public void objectPass(ShopList ShopList) {//Method to pass an object 
		newShopList = ShopList;
	}

}
