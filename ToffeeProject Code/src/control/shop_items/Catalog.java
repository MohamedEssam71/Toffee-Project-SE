package control.shop_items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
/**
 * <h3>Catalog Class</h3>
 * The Catalog class represents a
 * collection of items in the store.<br>
 * It provides the following methods:
 * <ul>
 *     <li>Search by name</li>
 *     <li>Search by brand</li>
 *     <li>Setters and getters for the items list</li>
 * </ul>
 * @author Rawan Younis
 * */
public class Catalog {
    private final HashMap<String, Item> itemsList  = new HashMap<>();

    /**
     * This method searches for items by brand name
     * @param brand the brand name to search for its items
     * @return ArrayList containing the items with the specified brand name
     * */
    public ArrayList<Item> searchByBrand(String brand){
        ArrayList<Item> targetItems = new ArrayList<>();
        for(Item item: itemsList.values()){
            if(Objects.equals(item.getBrand(), brand)){
                targetItems.add(item);
            }
        }
        return targetItems;
    }

    /**
     * This method searches for an item by name.
     * @param name the item's name to search for
     * @return Item with the specified name
     * */
    public Item searchByName(String name){
        return itemsList.get(name);
    }
    public HashMap<String,Item> getItems(){
        return itemsList;
    }
    public void setItems(ArrayList<Item> items){
        for(Item item : items){
            itemsList.put(item.getName(),item);
        }
    }

}
