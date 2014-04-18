package com.restaurant.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.restaurant.io.IOOperations;
import com.restaurant.menu.Item;
import com.restaurant.menu.Restaurant;
import com.restaurant.utils.ConstantsTest.Food;
import com.restaurant.utils.ConstantsTest.TestData;

/**
 * This
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 11, 2014
 */
public class CostSaverTest {

	@Test
	public void test1() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.FANCY_EUPROPIAN_WATER));
		requestedItems.add(new Item(Food.EXTREME_FAJITA));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE1);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 6);
		Assert.assertTrue(bestDeal.getDealPrice() == 11.0);
	}

	@Test
	public void test2() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.STEAK_SALAD));
		requestedItems.add(new Item(Food.WINE_SPRITZER));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE2);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 4);
		Assert.assertTrue(bestDeal.getDealPrice() == 4.0);
	}

	@Test
	public void test3() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.STEAK_SALAD));
		requestedItems.add(new Item(Food.WINE_SPRITZER));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE3);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 5);
		Assert.assertTrue(bestDeal.getDealPrice() == 4.0);
	}

	@Test
	public void test4() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.STEAK_SALAD));
		requestedItems.add(new Item(Food.WINE_SPRITZER));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE4);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 4);
		Assert.assertTrue(bestDeal.getDealPrice() == 2.5);
	}

	@Test
	public void test5() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.STEAK_SALAD));
		requestedItems.add(new Item(Food.WINE_SPRITZER));
		requestedItems.add(new Item(Food.FANCY_EUPROPIAN_WATER));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE5);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 4);
		Assert.assertTrue(bestDeal.getDealPrice() == 6.0);
	}

	@Test
	public void test6() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.EXTREME_FAJITA));
		requestedItems.add(new Item(Food.FANCY_EUPROPIAN_WATER));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE6);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 6);
		Assert.assertTrue(bestDeal.getDealPrice() == 11.0);
	}

	@Test
	public void test7() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.BURGER));
		requestedItems.add(new Item(Food.TOFU_LOG));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE7);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 2);
		Assert.assertTrue(bestDeal.getDealPrice() == 11.5);
	}

	@Test
	public void test8() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.CHEF_SALAD));
		requestedItems.add(new Item(Food.WINE_SPRITZER));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE8);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 0);
		Assert.assertTrue(bestDeal.getDealPrice() == 0);
	}

	@Test
	public void test9() {
		Set<Item> requestedItems = new HashSet<Item>();
		requestedItems.add(new Item(Food.FANCY_EUPROPIAN_WATER));
		requestedItems.add(new Item(Food.EXTREME_FAJITA));

		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(TestData.TEST_INPUT_FILE9);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);

		Assert.assertEquals(bestDeal.getRestaurantId(), 6);
		Assert.assertTrue(bestDeal.getDealPrice() == 11.0);
	}

}
