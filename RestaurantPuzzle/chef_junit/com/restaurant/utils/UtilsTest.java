package com.restaurant.utils;

import java.util.HashSet;
import java.util.Set;

import com.restaurant.menu.Item;
import com.restaurant.menu.ItemsOnMenu;

/**
 * This class contains the utility methods that are very useful, you know..
 * 
 * @author <a href="mailto:sekhar@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 11, 2014
 */
public class UtilsTest {

	/**
	 * get a set of Items from the given labels
	 * 
	 * @param labels
	 * @return
	 */
	public static Set<Item> getItems(Set<String> labels) {
		Set<Item> items = new HashSet<Item>(labels.size());
		for (String label : labels) {
			items.add(new Item(label));
		}
		return items;
	}

	/**
	 * Test whether the two give menu are equal or not
	 * 
	 * @param beforeMenu
	 * @param afterMenu
	 * @return
	 */
	public static boolean isEqual(Set<ItemsOnMenu> beforeMenu, Set<ItemsOnMenu> afterMenu) {
		if (beforeMenu == null && afterMenu == null) {
			return true;
		}
		if (beforeMenu == null || afterMenu == null) {
			return false;
		}
		if (beforeMenu.size() != afterMenu.size()) {
			return false;
		}
		for (ItemsOnMenu itemsOnMenu : beforeMenu) {
			if (!afterMenu.contains(itemsOnMenu)) {
				return false;
			}
		}
		return true;
	}
}
