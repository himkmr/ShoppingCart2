import java.io.Serializable;


public class CartItem implements Serializable{
String item_name;
double  item_price;
int quantity;
int item_id;

public String getItem_name() {
	return item_name;
}

public void setItem_name(String item_name) {
	this.item_name = item_name;
}

public double getItem_price() {
	return item_price;
}

public void setItem_price(double item_price) {
	this.item_price = item_price;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public int get_Item_id() {
	return item_id;
}

public void set_Item_id(int id) {
	this.item_id = id;
}

public static CartItem getItem(String name, double price, int quantity, int id)
{
	CartItem c = new CartItem();
	c.item_name = name;
	c.item_price = price;
	c.quantity = quantity;
	c.item_id =id;
	return c;
}
}
