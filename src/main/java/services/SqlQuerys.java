package services;

public class SqlQuerys {
    public final static String ADDITEM = "INSERT INTO items (item_name, item_price, item_count) VALUES (?, ?, ?)";
    public final static String FINDALLITEMS = "SELECT item_id, item_name, item_count, item_price FROM items";
    public final static String DELETE = "DELETE FROM items WHERE item_id =";
    public final static String UPDATE = "UPDATE items SET \"item_name\"= \'*name*\', \"item_price\"= *price*, \"item_count\"= *count* WHERE \"item_id\"= *id* ";
}
