package control.shop_items;

import control.Authentication.AuthenticationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Catalog {
    private final HashMap<String, Item> itemsList  = new HashMap<>();
    public ArrayList<Item> searchByBrand(String brand){
        ArrayList<Item> targetItems = new ArrayList<>();
        for(Item item: itemsList.values()){
            if(Objects.equals(item.getBrand(), brand)){
                targetItems.add(item);
            }
        }
        return targetItems;
    }
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
