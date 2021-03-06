package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.filter.FieldFilter;
import ch.hoene.perzist.access.filter.Operator;
import ch.hoene.perzist.access.sort.SortOrder;
import ch.hoene.perzist.jdbc.db.Tables;
import ch.hoene.perzist.jdbc.db.db2instance.Db2Product;
import ch.hoene.perzist.model.Product;
import ch.hoene.perzist.source.relational.FieldSortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class DatabaseJdbcTest {

    final TestDatabaseJdbc database = new TestDatabaseJdbc();

    final FieldFilter<Tables.Products, Product, String> cat1Filter =
            new FieldFilter<Tables.Products, Product, String>(
                    Tables.Products.CATALOG,
                    Operator.EQ,
                    "cat1"
            );

    @Before
    public void setUp() throws Exception {
        database.create();
    }

    @After
    public void tearDown() throws Exception {
        database.drop(Tables.PRODUCTS);
    }

    @Test
    public void testPush() throws Exception {
        assertTrue(database.persistProduct(new Product(0, "desc0", "cat0")));
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(database.isEmpty(null, Tables.PRODUCTS));
        assertTrue(database.persistProduct(new Product(0, "desc0", "cat0")));
        assertFalse(database.hasNoProducts());
    }

    @Test
    public void testCount() throws Exception {

        assertEquals(0, database.count(null, Tables.PRODUCTS));
        assertTrue(database.persistProduct(new Product(0, "desc0", "cat0")));
        assertEquals(1, database.count(null, Tables.PRODUCTS));
        assertTrue(database.persistProduct(new Product(1, "desc1", "cat1")));
        assertEquals(2, database.count(null, Tables.PRODUCTS));

        assertEquals(1, database.count(cat1Filter, Tables.PRODUCTS));
        assertTrue(database.persistProduct(new Product(2, "desc2", "cat1")));
        assertEquals(2, database.count(cat1Filter, Tables.PRODUCTS));
    }

    @Test
    public void testGetList() throws Exception {
        Set<Product> products = new HashSet<Product>();
        products.add(new Product(0, "desc0", "cat0"));
        products.add(new Product(1, "desc1", "cat0"));
        products.add(new Product(2, "desc2", "cat1"));
        products.add(new Product(3, "desc3", "cat1"));
        products.add(new Product(4, "desc3", "cat1"));

        for(Product product : products)
        {
            database.persistProduct(product);
        }

        Set<Product> productsExpected = new HashSet<Product>(products);
        List<Product> storedProducts = database.getList(null, new Db2Product(), Tables.PRODUCTS, Tables.PRODUCTS);
        for(Product storedProduct : storedProducts)
        {
            productsExpected.remove(storedProduct);
        }
        assertTrue(productsExpected.size() == 0);

        //with filter
        Set<Product> productsNotExpected = new HashSet<Product>(products);
        storedProducts = database.getList(cat1Filter, new Db2Product(), Tables.PRODUCTS, Tables.PRODUCTS);
        for(Product storedProduct : storedProducts)
        {
            productsNotExpected.remove(storedProduct);
        }
        assertTrue(productsNotExpected.size() == 2);

        //with sort
        storedProducts = database.getList(null, new Db2Product(), Tables.PRODUCTS, Tables.PRODUCTS, new FieldSortOrder(Tables.Products.DESC, SortOrder.ASC));
        assertEquals(storedProducts.get(0).getDescription(), "desc0");
        assertEquals(storedProducts.get(1).getDescription(), "desc1");
        assertEquals(storedProducts.get(2).getDescription(), "desc2");
        assertEquals(storedProducts.get(3).getDescription(), "desc3");
        assertEquals(storedProducts.get(4).getDescription(), "desc3");

        storedProducts = database.getList(null, new Db2Product(), Tables.PRODUCTS, Tables.PRODUCTS, new FieldSortOrder(Tables.Products.DESC, SortOrder.DESC));
        assertEquals(storedProducts.get(0).getDescription(), "desc3");
        assertEquals(storedProducts.get(1).getDescription(), "desc3");
        assertEquals(storedProducts.get(2).getDescription(), "desc2");
        assertEquals(storedProducts.get(3).getDescription(), "desc1");
        assertEquals(storedProducts.get(4).getDescription(), "desc0");
    }

    @Test
    public void testDrop() throws Exception {
        database.drop(Tables.PRODUCTS);
        database.create();
    }
}
