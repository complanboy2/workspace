package com.restaurant.utils;

import java.util.Set;

import com.restaurant.menu.Item;

/**
 * This is a data holder for the Best Deal.
 * Holds the price and the restaurant id for the ordered items.
 *
 * @author <a href="mailto:sekhar@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 10, 2014
 */
public class BestDeal {

	private int restaurantId;
	private Set<Item> items;
	private double dealPrice;

	public BestDeal(Set<Item> requiredItems) {
		this.items = requiredItems;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public Set<Item> getItems() {
		return items;
	}

	public double getDealPrice() {
		return dealPrice;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public void setDealPrice(double dealPrice) {
		this.dealPrice = dealPrice;
	}
}
