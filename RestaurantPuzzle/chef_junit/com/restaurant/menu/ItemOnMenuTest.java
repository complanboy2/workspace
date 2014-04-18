package com.restaurant.menu;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.restaurant.utils.ConstantsTest.Food;

/**
 * This class tests the menu prepation
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 11, 2014
 */
public class ItemOnMenuTest {

	/**
	 * tests the menu prepration
	 * 
	 */
	@Test
	public void testMenuPreparation() {
		Set<String> itemNames = new HashSet<String>();
		itemNames.add(Food.BURGER);
		itemNames.add(Food.PEPSI);

		ItemsOnMenu kfcMenu = new ItemsOnMenu(itemNames, 170);
		Assert.assertTrue(kfcMenu.getPrice() == 170);

		Set<Item> itemsOnMenu = new HashSet<Item>();
		itemsOnMenu.add(new Item(Food.BURGER));
		itemsOnMenu.add(new Item(Food.PEPSI));
		Assert.assertEquals(kfcMenu.getItems(), itemsOnMenu);
	}
}
