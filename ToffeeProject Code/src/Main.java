import actors.Attachtments.Order;
import actors.User;
import control.shop_items.Cart;
import control.shop_items.Item;
import control.shop_items.ItemStatus;

public class Main {
    public static void main(String[] args) {
        User user = new User("Name", "email", "pass", "0123");
        Cart cart = new Cart();
        user.setCart(cart);
        Item item = new Item("sweet", "sweet", "link", "black", "sealed", ItemStatus.ON_SALE, 23.3),
                item2 = new Item("sweet", "sweet", "link", "black", "sealed", ItemStatus.ON_SALE, 23.3);
        cart.addToCart(item);
        cart.addToCart(item);
        cart.addToCart(item);
        cart.addToCart(item);
        cart.addToCart(item);
        cart.addToCart(item2);

        Order order = new Order(user);
        order.showOrderDetails();
    }
}