package control.shop_items;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Item>itemsList;

    public void addToCart(Item item){
        itemsList.add(item);
    };
    public void removeFromCart(Item item){
        itemsList.remove(item);
    };


}
