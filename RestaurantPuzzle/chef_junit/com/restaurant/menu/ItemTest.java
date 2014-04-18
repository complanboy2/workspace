package com.restaurant.menu;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class tests the items
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since April 11, 2014
 */
public class ItemTest {

	@Test
	public void testEquals() {
		String itemLabel = "Birayni";

		Item item1 = new Item(itemLabel);
		Assert.assertEquals(item1.getLabel(), itemLabel);

		Item item2 = new Item(itemLabel);
		Assert.assertTrue(item1.equals(item2));

		item2.setLabel("Veg");
		Assert.assertTrue(!item1.equals(item2));
	}
}
