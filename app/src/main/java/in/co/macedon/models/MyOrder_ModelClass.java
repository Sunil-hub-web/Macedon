package in.co.macedon.models;

public class MyOrder_ModelClass {

    String itemNmae,orderId,orderDate,itemPrice;
    int image;

    public MyOrder_ModelClass(int image,String itemNmae, String orderId, String orderDate, String itemPrice) {
        this.itemNmae = itemNmae;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.itemPrice = itemPrice;
        this.image = image;
    }

    public String getItemNmae() {
        return itemNmae;
    }

    public void setItemNmae(String itemNmae) {
        this.itemNmae = itemNmae;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
