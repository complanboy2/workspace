package com.restaurant.menu;

import java.util.HashSet;
import java.util.Set;

import com.restaurant.utils.Utils;

/**
 * This is a data holder for the Restaurant type
 * 
 * @author <a href="mailto:sekhar@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 10, 2014
 */
public class Restaurant {

	// Menu which holds the items (contains individual items as well as value
	// meals)
	private final Set<ItemsOnMenu> menu = new HashSet<ItemsOnMenu>();
	private final int id;

	public Restaurant(int id) {
		super();
		this.id = id;
	}

	/**
	 * Add given items to the menu of this Restaurant
	 * 
	 * @param items
	 */
	public void addItemsToMenu(ItemsOnMenu items) {
		menu.add(items);
	}

	/**
	 * Checks whether the given item is available on this Restaurant menu
	 * 
	 * @param item
	 * @return
	 */
	public boolean hasItemOnMenu(Item item) {
		return Utils.hasItemOnMenu(item, menu);
	}

	/**
	 * Checks whether the given items are available on this Restaurant menu
	 * 
	 * @param items
	 * @return
	 */
	public boolean hasItemsOnMenu(Set<Item> items) {
		return Utils.hasItemsOnMenu(items, menu);
	}

	/**
	 * Get the menu please..
	 * 
	 * @return
	 */
	public Set<ItemsOnMenu> getMenu() {
		return menu;
	}

	/**
	 * Restaurant identifier. In this case, we prefered Integers .. 1, 2, 3 (we
	 * can also use, primary numbers :-))
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
}
