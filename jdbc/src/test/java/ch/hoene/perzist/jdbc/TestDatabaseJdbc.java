package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.jdbc.db.Tables;
import ch.hoene.perzist.jdbc.db.instance2db.Product2Db;
import ch.hoene.perzist.model.Product;

import java.sql.SQLException;

public class TestDatabaseJdbc extends DatabaseJdbc {

    public TestDatabaseJdbc() {
        super("jdbc:hsqldb:mem:mymemdb");
    }

    public boolean hasNoProducts()
    {
        return isEmpty(null, Tables.PRODUCTS);
    }

    public boolean persistProduct(Product product)
    {
        return push(product, new Product2Db()) > 0;
    }

    //------------------------ Test Heplers

    public void create() throws SQLException
    {
        String createTracksSql = "CREATE TABLE " + Tables.PRODUCTS.getName() + " ("
                + Tables.Products.ID.getName() + " INTEGER, "
                + Tables.Products.DESC.getName() + " VARCHAR(255), "
                + Tables.Products.CATALOG.getName() + " VARCHAR(255), "
                + " PRIMARY KEY (" + Tables.Products.ID.getName() + "));";
        db.prepareStatement(createTracksSql).executeUpdate();
    }

}
