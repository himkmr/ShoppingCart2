import java.util.ArrayList;


public class Cart {
public static ArrayList<CartItem> list=new ArrayList<CartItem>();
public static ArrayList<CartItem> addToCart(CartItem item)
{
	list.add(item);
	return list;
}

public static  ArrayList<CartItem> getList()
{

	return list;
}
}
