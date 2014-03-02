package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.filter.FieldFilter;
import ch.hoene.perzist.access.filter.Operator;
import ch.hoene.perzist.jdbc.db.Tables;
import ch.hoene.perzist.model.Product;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class DatabaseJdbcTest {

    TestDatabaseJdbc database = new TestDatabaseJdbc();

    @Before
    public void setUp() throws Exception {
        database.create();
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

        final FieldFilter<Tables.Products, Product, String> desc1Filter =
            new FieldFilter<Tables.Products, Product, String>(
                Tables.Products.DESC,
                Operator.EQ,
                "desc1"
            );

        assertEquals(1, database.count(desc1Filter, Tables.PRODUCTS));
        assertTrue(database.persistProduct(new Product(2, "desc1", "cat2")));
        assertEquals(2, database.count(desc1Filter, Tables.PRODUCTS));
    }

    @Test
    public void testGetList() throws Exception {
        assertTrue(false);
    }

    @Test
    public void testDrop() throws Exception {
        assertTrue(false);
    }
}
