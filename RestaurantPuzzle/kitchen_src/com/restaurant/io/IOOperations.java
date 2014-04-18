package com.restaurant.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.restaurant.menu.ItemsOnMenu;
import com.restaurant.menu.Restaurant;
import com.restaurant.utils.BestDeal;
import com.restaurant.utils.Constants.IORelated;

/**
 * This class takes care of all the i/o operations
 * 
 * @author <a href="mailto:sekhar@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 10, 2014
 */
public class IOOperations {

	/**
	 * Read the input file by its name
	 * 
	 * @param fileName
	 * @return
	 */
	public static Map<Integer, Restaurant> readInputFile(String fileName) {
		BufferedReader br = null;
		Map<Integer, Restaurant> restaurantMap = new HashMap<Integer, Restaurant>();
		try {
			br = new BufferedReader(new FileReader(fileName));
			return buildDataFromInput(br);
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return restaurantMap;
	}

	/**
	 * Write the output in the required format
	 * 
	 * @param bestDeal
	 */
	public static void writeOutput(BestDeal bestDeal) {
		System.out.println(bestDeal.getRestaurantId() + "," + bestDeal.getDealPrice());
	}

	/**
	 * Build necessary data holder(structure) from the input data read
	 * 
	 * @param br
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private static Map<Integer, Restaurant> buildDataFromInput(BufferedReader br) throws NumberFormatException,
			IOException {
		Map<Integer, Restaurant> restaurantMap = new HashMap<Integer, Restaurant>();
		String line = "";

		while ((line = br.readLine()) != null && (line = line.trim()).length() > 0) {
			String[] values = line.split(IORelated.INPUT_FILE_SPLIT_BY);
			Integer restaurantId = Integer.parseInt(values[IORelated.RESTAURANT_ID_INDEX].trim());
			double price = Double.parseDouble(values[IORelated.ITEM_PRICE_INDEX].trim());
			int totalItems = values.length - IORelated.ITEM_LABEL_S_INDEX;
			Set<String> lables = new HashSet<String>(totalItems);
			if (values.length == IORelated.ARGUMENTS_LENGTH_INCASE_OF_SINGLE_ITEM) {
				lables.add(values[IORelated.ITEM_LABEL_S_INDEX].trim().toLowerCase());
			} else {
				int j = IORelated.ITEM_LABEL_S_INDEX + totalItems;
				for (int i = IORelated.ITEM_LABEL_S_INDEX; i < j; i++) {
					lables.add(values[i].trim().toLowerCase());
				}
			}
			ItemsOnMenu itemsOnMenu = new ItemsOnMenu(lables, price);
			Restaurant restaraunt = restaurantMap.get(restaurantId);
			if (restaraunt == null) {
				restaraunt = new Restaurant(restaurantId);
				restaurantMap.put(restaurantId, restaraunt);
			}
			restaraunt.addItemsToMenu(itemsOnMenu);
		}
		return restaurantMap;
	}
}
