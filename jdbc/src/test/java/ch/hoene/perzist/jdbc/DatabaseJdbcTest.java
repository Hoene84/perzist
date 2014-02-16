package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.model.Product;
import org.junit.Before;
import org.junit.Test;

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
        assertTrue(database.hasNoProducts());
        assertTrue(database.persistProduct(new Product(0, "desc0", "cat0")));
        assertFalse(database.hasNoProducts());
    }

    @Test
    public void testCount() throws Exception {
        assertTrue(false);
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
