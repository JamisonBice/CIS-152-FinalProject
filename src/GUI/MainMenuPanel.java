package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Jamison Bice
 *
 */
public class MainMenuPanel extends JPanel {

	JButton bAddNewList = new JButton("Create New List");// Inits of buttons
	JButton bViewList = new JButton("View List");
	JButton bViewAllLists = new JButton("ViewAllLists");

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {

		JLabel instructions = new JLabel(" SELECT AN OPTION TO CONTINUE ");// Label

		ButtonListener bl = new ButtonListener();// Ininits of the button listener to get input
		bAddNewList.addActionListener(bl);
		bViewList.addActionListener(bl);
		bViewAllLists.addActionListener(bl);

		JPanel buttons = new JPanel();// init

		buttons.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));// Format
		buttons.setLayout(new GridLayout(0, 1));

		add(instructions);// Adding the componenets
		buttons.add(bAddNewList);
		buttons.add(bViewList);
		buttons.add(bViewAllLists);

		add(buttons);
	}

	class ButtonListener implements ActionListener {// Listener for button action

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel newPanel = new JPanel();
			if (e.getSource() == bAddNewList) {// Goes to New Shop Panel when pressed
				newPanel = new NewShopListPanel();

			} else if (e.getSource() == bViewList) {// Goes to Shop View when pressed
				try {
					newPanel = new ShoppingListPanel();
				} catch (EmptyStackException | ExceptionInInitializerError ex) {// Error catch
					JOptionPane.showMessageDialog(null, "No List", "Warning", JOptionPane.WARNING_MESSAGE);
					System.exit(1);
				}

			} else if (e.getSource() == bViewAllLists) {// Goes to Shopping lists view when pressed
				try {
					newPanel = new ViewShoppingListPanel();
				} catch (NullPointerException | EmptyStackException | ExceptionInInitializerError ex) {// Error Catch
					JOptionPane.showMessageDialog(null, "No List", "Warning", JOptionPane.WARNING_MESSAGE);
					System.exit(1);
				}
			}

			sendToNewPanel(newPanel);

		}

		private void sendToNewPanel(JPanel newPanel) {// Method to "send" the user to the next panel
			removeAll();// Clears then creates the new panel
			setVisible(false);
			add(newPanel);
			validate();
			setVisible(true);

		}

	}

}
