package ch.hoene.perzist.jdbc.db;

import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.model.Product;
import ch.hoene.perzist.source.relational.Field;
import ch.hoene.perzist.source.relational.FieldPersistable;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.fieldtype.FieldPersistableAsInteger;
import ch.hoene.perzist.source.relational.fieldtype.FieldPersistableAsString;

import java.util.Arrays;
import java.util.List;

public class Tables {

    public final static Tables.Products PRODUCTS = new Tables.Products();

    public static final class Products extends Table {
        public static final FieldPersistable<Product, Integer> ID = new FieldPersistableAsInteger<Product>("id", new Creator<Integer, Product>() {
            public Integer create(Product product) {
                return product.getId();
            }
        });

        public static final FieldPersistable<Product, String> DESC = new FieldPersistableAsString<Product>("desc", new Creator<String, Product>() {
            public String create(Product product) {
                return product.getDescription();
            }
        });

        public static final FieldPersistable<Product, String> CATALOG = new FieldPersistableAsString<Product>("catalog", new Creator<String, Product>() {
            public String create(Product product) {
                return product.getCatalog();
            }
        });

        private Products() {
            super("Products");
        }

        @Override
        public List<Field> getFields() {
            return Arrays.asList(new Field[]{ID});
        }
    }
}