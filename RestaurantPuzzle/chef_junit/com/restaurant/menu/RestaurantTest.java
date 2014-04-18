package com.restaurant.menu;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.restaurant.utils.ConstantsTest.Food;
import com.restaurant.utils.UtilsTest;

/**
 * This class tests the restaurant preparation etc
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 11, 2014
 */
public class RestaurantTest {

	@Test
	public void testRestaurantId() {
		Restaurant restaraunt = new Restaurant(2);
		Assert.assertEquals(restaraunt.getId(), 2);
	}

	/**
	 * Tests whether the restaurant has the orderedItem on menu
	 * 
	 */
	@Test
	public void testHasItemOnMenu() {
		Restaurant restaraunt = new Restaurant(2);
		Set<String> labels = new HashSet<String>();
		labels.add(Food.CHICKEN_TICKERS);

		Item item = new Item(Food.CHICKEN_TICKERS);
		ItemsOnMenu itemsOnMenu = new ItemsOnMenu(labels, 40);
		restaraunt.addItemsToMenu(itemsOnMenu);
		Assert.assertTrue(restaraunt.hasItemOnMenu(item));
	}

	/**
	 * Tests whether the restaurant has the orderedItems on the menu
	 * 
	 */
	@Test
	public void testHasItemsOnMenu() {
		Restaurant restaraunt = new Restaurant(2);
		Set<String> labels = new HashSet<String>();

		labels.add(Food.CHICKEN_BURGER);
		labels.add(Food.CHICKEN_SANDWICH);
		labels.add(Food.CHICKEN_KABABS);
		restaraunt.addItemsToMenu(new ItemsOnMenu(labels, 40));

		Set<Item> setItems = UtilsTest.getItems(labels);
		Assert.assertTrue(restaraunt.hasItemsOnMenu(setItems));
	}

	/**
	 * Tests whether addition of items to menu is proper
	 * 
	 */
	@Test
	public void testAddItemsToMenu() {
		Restaurant restaurant = new Restaurant(2);

		Set<String> labels = new HashSet<String>();
		labels.add(Food.CHICKEN_BURGER);
		labels.add(Food.CHICKEN_SANDWICH);
		labels.add(Food.CHICKEN_KABABS);

		ItemsOnMenu itemsOnMenu = new ItemsOnMenu(labels, 40);
		restaurant.addItemsToMenu(itemsOnMenu);

		Set<ItemsOnMenu> beforeMenu = new HashSet<ItemsOnMenu>();
		beforeMenu.add(itemsOnMenu);

		Set<ItemsOnMenu> afterMenu = restaurant.getMenu();
		Assert.assertTrue(UtilsTest.isEqual(beforeMenu, afterMenu));
	}
}
