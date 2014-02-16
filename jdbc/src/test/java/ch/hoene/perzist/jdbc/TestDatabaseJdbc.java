package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.model.Product;
import ch.hoene.perzist.source.relational.Field;
import ch.hoene.perzist.source.relational.FieldPersistable;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.fieldtype.FieldPersistableAsInteger;
import ch.hoene.perzist.source.relational.fieldtype.FieldPersistableAsString;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return push(product, new ProductToDbMapper()) > 0;
    }

    //-------------------------- TABLE DEFINITIONS

    private static class Tables
    {
        public final static Products PRODUCTS = new Products();

        public static final class Products extends Table
        {
            public static final FieldPersistable<Product, Integer> ID = new FieldPersistableAsInteger<Product>("id", new Creator<Integer, Product>(){
                public Integer create(Product product)
                {
                    return product.getId();
                }
            });

            public static final FieldPersistable<Product, String> DESC = new FieldPersistableAsString<Product>("desc", new Creator<String, Product>(){
                public String create(Product product)
                {
                    return product.getDescription();
                }
            });

            public static final FieldPersistable<Product, String> CATALOG = new FieldPersistableAsString<Product>("catalog", new Creator<String, Product>(){
                public String create(Product product)
                {
                    return product.getCatalog();
                }
            });

            private Products()
            {
                super("Products");
            }

            @Override
            public List<Field> getFields()
            {
                return Arrays.asList(new Field[]{ID});
            }
        }
    }

    private class ProductToDbMapper implements Mapping<Table, List<Object>, Product>
    {
        public Table get() {
            return Tables.PRODUCTS;
        }

        public List<Object> create(Product product)
        {
            final List<Object> values = new ArrayList<Object>();

            values.add(product.getId());
            values.add(product.getDescription());
            values.add(product.getCatalog());

            return  values;
        }
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
