package com.restaurant.io;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.restaurant.menu.Item;
import com.restaurant.menu.Restaurant;
import com.restaurant.utils.BestDeal;
import com.restaurant.utils.Utils;

/**
 * this class helps in testing the application
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 11, 2014
 */
public class Test {
	
	public static void main(String[] args) {
		if(args == null || args.length < 2) {
			System.err.println("Please provide the correct format");
			System.out.println("ClassName data.csv item[s]");
			return;
		}
		
		String inputFileName = args[0];
		Set<Item> requestedItems = new HashSet<Item>();
		if(args.length == 2) {
			requestedItems.add(new Item(args[1].trim()));
		} else {
			for(int i = 1; i < args.length; i++) {
				requestedItems.add(new Item(args[i]));
			}
		}
		
		Map<Integer, Restaurant> restaurantMap = IOOperations.readInputFile(inputFileName);
		BestDeal bestDeal = Utils.findBestDeal(restaurantMap, requestedItems);
		IOOperations.writeOutput(bestDeal);
	}
}
