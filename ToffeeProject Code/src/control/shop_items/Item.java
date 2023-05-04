package control.shop_items;
public class Item {
    private String name;
    private String description;
    private String image;
    private String brand;
    private String unitType;
    private ItemStatus itemStatus;
    private double price;

    public Item(String name, String description, String image, String brand,
                String unitType, ItemStatus itemStatus, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.brand = brand;
        this.unitType = unitType;
        this.itemStatus = itemStatus;
        this.price = price;
    }

    public void applyDiscount(double discountAmount) {
        itemStatus = ItemStatus.onSale;
        price *= 100 - discountAmount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getBrand() {
        return brand;
    }

    public String getUnitType() {
        return unitType;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
