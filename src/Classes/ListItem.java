
package Classes;

/**
 * @author Jamison Bice
 *
 */
public class ListItem {

	private String itemName;// Variables Name
	private String store;// Store Name
	private double price;// Price of item
	private int amount;// Amount of item

	/**
	 * @param itemName
	 * @param store
	 * @param price
	 * @param amount
	 * @param checkOff
	 */
	public ListItem(String itemName, String store, double price, int amount) {// COnstructor
		setItemName(itemName);
		setStore(store);
		setPrice(price);
		setAmount(amount);
	}

	/**
	 * 
	 */
	public ListItem() {// Default Constructor
		setItemName(null);
		setStore(null);
		setPrice(0);
		setAmount(0);
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {// Getters and Setters
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the store
	 */
	public String getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {// Standard To STring
		return "ListItem [itemName=" + itemName + ", store=" + store + ", price=" + price + ", amount=" + amount + "]";
	}

}
