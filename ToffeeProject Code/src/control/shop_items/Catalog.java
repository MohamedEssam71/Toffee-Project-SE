package control.shop_items;

import control.Authentication.AuthenticationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Catalog {
    private final HashMap<String, Item> itemsList  = new HashMap<>();
    public void searchByBrand(String brand){
//        ArrayList<Item> targetItems = new ArrayList<>();
        for(Item item: itemsList.values()){
            if(Objects.equals(item.getBrand(), brand)){
//                targetItems.add(item);
//                ToDo: format output
                System.out.println(item.getName() + " "+ item.getBrand());
            }
        }
//        return targetItems;
    }
    public void searchByName(String name){
        Item searchedFor = itemsList.get(name);
        if (searchedFor != null) {
            //ToDo: format output
            System.out.println(name + " " + searchedFor.getBrand());
        }else{
            System.out.println("No items found!");
        }
        //return itemsList.get(name);
    }

//    public void filterByCategory(){
//
//    }
    public HashMap<String,Item> getItems(){
        return itemsList;
    }
    public void addItem(Item item){
        itemsList.put(item.getName(),item);
    }
    public void removeItem(Item item){
        itemsList.remove(item.getName());
    }
    public void updateItem(Item newItem){
        Item oldItem = itemsList.get(newItem.getName());
        if (oldItem != null) {
            itemsList.put(oldItem.getName(),newItem);
            System.out.println("Item updated successfully!");
        }else{
            System.out.println("Item not found!");
            //add it?
        }
    }
//        public void setItemQuantity(Item item){
//
//    }
    public static void main(String[] args) {
        Item item = new Item("choco","des","image"
                ,"brand1","Kilos",null,10.5);

        Item item1 = new Item("chocopop","des","image"
                ,"brand1","Kilos",null,10.5);

        Item item2 = new Item("icecream","des","image"
                ,"brand2","Kilos",null,10.5);

        Catalog catalog = new Catalog();
        catalog.addItem(item);
        catalog.addItem(item1);
        catalog.addItem(item2);

//        catalog.searchByBrand("brand1");
        catalog.searchByName("chocolate");
        catalog.searchByName("choco");

    }
}
