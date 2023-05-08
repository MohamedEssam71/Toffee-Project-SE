package control.shop_items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Item, Integer> itemsList = new HashMap<>();

    public void addToCart(Item item){
        itemsList.put(item, itemsList.getOrDefault(item, 0) + 1);
    }
    public void removeFromCart(Item item){
        itemsList.remove(item);
    }

    public Map<Item, Integer> getItemsList() {
        return itemsList;
    }
    public int getQty(Item item) { return itemsList.get(item); }
}
