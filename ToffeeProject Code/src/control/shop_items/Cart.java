package control.shop_items;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents an online cart.
 * It has a list of items to be purchased by a
 * certain user and allows user to add/remove
 * items to/from the cart.
 *
 * @author Maya Ayman
 * @author Mohamed Essam
 */
public class Cart {
    private Map<Item, Integer> itemsList = new HashMap<>();

    /**
     * This method allows the user to add an
     * item to his cart.
     * @param item
     */
    public void addToCart(Item item){
        itemsList.put(item, itemsList.getOrDefault(item, 0) + 1);
    }
    /**
     * This method allows the user to remove an
     * item from his cart.
     * @param item
     */
    public void removeFromCart(Item item){
        itemsList.remove(item);
    }

    public Map<Item, Integer> getItemsList() {
        return itemsList;
    }
}
