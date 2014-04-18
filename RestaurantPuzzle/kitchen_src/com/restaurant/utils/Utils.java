package com.restaurant.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.restaurant.menu.Item;
import com.restaurant.menu.ItemsOnMenu;
import com.restaurant.menu.Restaurant;

/**
 * This class contains the utility methods that are very useful, you know..
 * 
 * @author <a href="mailto:sekhar@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 10, 2014
 */
public class Utils {

	/**
	 * Find whether the given items are available on the menu or not
	 * 
	 * @param items
	 * @param menu
	 * @return
	 */
	public static boolean hasItemsOnMenu(Set<Item> items, Set<ItemsOnMenu> menu) {
		for (Item item : items) {
			if (!hasItemOnMenu(item, menu)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Finds whether the given item is present on the menu or not
	 * 
	 * @param item
	 * @param menu
	 * @return
	 */
	public static boolean hasItemOnMenu(Item item, Set<ItemsOnMenu> menu) {
		for (ItemsOnMenu itemsOnMenu : menu) {
			if (itemsOnMenu.getItems().contains(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find the best deal possible for each restaurant, which has all the
	 * ordered items on menu. And then find the best deal among the best
	 * possible deals from each restaurant
	 * 
	 * @param restMap
	 * @param orderedItems
	 * @return
	 */
	public static BestDeal findBestDeal(Map<Integer, Restaurant> restMap, Set<Item> orderedItems) {
		BestDeal bestDeal = new BestDeal(orderedItems);
		for (Restaurant restaurant : restMap.values()) {
			if (restaurant.hasItemsOnMenu(orderedItems)) {
				double price = Utils.findBestDeal(restaurant.getMenu(), orderedItems);
				if (bestDeal.getDealPrice() == 0 || bestDeal.getDealPrice() > price) {
					bestDeal.setRestaurantId(restaurant.getId());
					bestDeal.setDealPrice(price);
				}
			}
		}
		return bestDeal;
	}

	/**
	 * We are going with each restaurant. We consider the prices from min to
	 * max. We look for a matched ordered items from this min price menu and
	 * keep adding until we find a all the matches but with better deal.
	 * 
	 * Map looks like
	 * 
	 * 1 a 
	 * 
	 * 2 (a,b), c 
	 * 
	 * 3 (a,b)
	 * 
	 * We pick 2 (a,b), c
	 * 
	 * @param menu
	 * @param itemList
	 * @return
	 */
	private static double findBestDeal(Set<ItemsOnMenu> menu, Set<Item> itemList) {
		Map<Double, List<ItemsOnMenu>> sortedItemsMap = getItemMapSortedOnPrice(menu);
		List<Double> sortedPrices = new ArrayList<Double>(sortedItemsMap.keySet());

		double minprice = 0;
		for (int i = 0; i < sortedPrices.size(); i++) {
			List<ItemsOnMenu> matchList = new ArrayList<ItemsOnMenu>();
			Set<Item> tempList = new TreeSet<Item>();
			double price = 0.0;
			for (int j = i; j < sortedPrices.size(); j++) {
				matchList = getItemsOnMenuListSortedOnMatchCount(sortedItemsMap.get(sortedPrices.get(j)), itemList);
				for (ItemsOnMenu itemsOnMenu : matchList) {
					boolean flag = false;
					for (Item item : itemsOnMenu.getItems()) {
						if (itemList.contains(item) && !tempList.contains(item)) {
							tempList.add(item);
							flag = true;
						}
					}
					if (flag) {
						price += sortedPrices.get(j);
					}
				}
				if (tempList.containsAll(itemList)) {
					if (minprice == 0 && price != 0.0) {
						minprice = price;
					} else {
						if (price < minprice && price != 0.0) {
							minprice = price;
						}
					}
				}
			}
		}
		return minprice;
	}

	/**
	 * Get the items map from the menu such that it should key is: price and
	 * value is: list of ItemsOnMenu
	 * 
	 * So, we are combining all the itemsOnMenu objects having the same price
	 * and sorting the map on price.
	 * 
	 * @param menu
	 * @return
	 */
	private static Map<Double, List<ItemsOnMenu>> getItemMapSortedOnPrice(Set<ItemsOnMenu> menu) {
		Map<Double, List<ItemsOnMenu>> sortedItemsMap = new TreeMap<Double, List<ItemsOnMenu>>();
		for (ItemsOnMenu itemsOnMenu : menu) {
			List<ItemsOnMenu> priceList = sortedItemsMap.get(itemsOnMenu.getPrice());
			if (priceList == null) {
				priceList = new ArrayList<ItemsOnMenu>();
				sortedItemsMap.put(itemsOnMenu.getPrice(), priceList);
			}
			priceList.add(itemsOnMenu);
		}
		return sortedItemsMap;
	}

	/**
	 * This method returns the ItemsOnMenu list which has matches from itemList.
	 * And the result list is sorted on number of matches(match sort)
	 * 
	 * @param list
	 * @param itemList
	 * @return
	 */
	private static List<ItemsOnMenu> getItemsOnMenuListSortedOnMatchCount(List<ItemsOnMenu> list, Set<Item> itemList) {
		List<ItemsOnMenu> matchList = new ArrayList<ItemsOnMenu>();
		for (ItemsOnMenu itemsOnMenu : list) {
			int matchCount = 0;
			for (Item item : itemsOnMenu.getItems()) {
				if (itemList.contains(item)) {
					matchCount++;
				}
			}
			itemsOnMenu.setMatchCount(matchCount);
			if (matchCount != 0) {
				matchList.add(itemsOnMenu);
			}
		}
		Collections.sort(matchList, ItemsOnMenu.COMPARE_MATCH_COUNT_IN_REVERSE);
		return matchList;
	}	
}
