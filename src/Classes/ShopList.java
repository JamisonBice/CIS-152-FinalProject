
package Classes;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author Jamison Bice
 *
 */
public class ShopList {

	private String listName;// Variables name
	private Date tripDate;// Date when created
	private LinkedList<ListItem> items;// Linked List of List Items

	/**
	 * @param iD
	 * @param listName
	 * @param tripDate
	 */
	public ShopList(String listName, Date tripDate) {// Constructor
		setListName(listName);
		setTripDate(tripDate);
		items = new LinkedList<ListItem>();
	}

	/**
	 * 
	 */
	public ShopList() {// Default Constructor
		items = new LinkedList<ListItem>();

	}

	/**
	 * @return the listName
	 */
	public String getListName() {// Getters and Setters
		return listName;
	}

	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}

	/**
	 * @return the tripDate
	 */
	public Date getTripDate() {
		return tripDate;
	}

	/**
	 * @param tripDate the tripDate to set
	 */
	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	/**
	 * @return the items
	 */
	public LinkedList<ListItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(LinkedList<ListItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {// To Striong Standard
		return "ShopList [listName=" + listName + ", tripDate=" + tripDate + ", items=" + items + "]";
	}

	/**
	 * @param item
	 */
	public void addItem(ListItem item) { // Method used to add items to the Linked List
		items.addFirst(item);
	}

}
