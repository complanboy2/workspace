package com.restaurant.menu;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a data holder for menu items. This can also contain combos(value
 * meals), family packs etc..
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 10, 2014
 */
public class ItemsOnMenu {

	private double price;
	private int matchCount;
	private Set<Item> items;

	public ItemsOnMenu(Set<String> lables, double price) {
		items = new HashSet<Item>(lables.size());
		for (String string : lables) {
			items.add(new Item(string));
		}
		this.price = price;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	/**
	 * This comparator matched any two ItemsOnMenu objects as per their
	 * matchCount value
	 */
	public static final Comparator<ItemsOnMenu> COMPARE_MATCH_COUNT_IN_REVERSE = new Comparator<ItemsOnMenu>() {
		@Override
		public int compare(ItemsOnMenu o1, ItemsOnMenu o2) {
			if (o1.matchCount == o2.matchCount) {
				return 0;
			}
			if (o1.matchCount > o2.matchCount) {
				return -1;
			}
			return 1;
		}
	};
}
