
package Classes;

import java.util.Stack;

/**
 * @author Jamison Bice
 *
 */

public class Shopper {
	private static Stack<ShopList> shoppingLists = new Stack<ShopList>();// Variable used to store the all the shopping
																			// lists

	/**
	 * 
	 */
	public Shopper() {
		shoppingLists = new Stack<ShopList>();
	}

	/**
	 * @return the shoppingLists
	 */
	public static Stack<ShopList> getShoppingLists() {
		return shoppingLists;
	}

	/**
	 * @param shoppingLists the shoppingLists to set
	 */
	public void setShoppingLists(Stack<ShopList> shoppingLists) {
		Shopper.shoppingLists = shoppingLists;
	}

	@Override
	public String toString() {
		return "Shopper [shoppingLists=" + shoppingLists + "]";
	}

	public static void addNewList(ShopList newList) {
		shoppingLists.push(newList);
	}

	public static ShopList peekList() {
		return shoppingLists.peek();
	}

}
