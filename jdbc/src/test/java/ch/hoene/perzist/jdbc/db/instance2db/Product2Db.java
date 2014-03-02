package ch.hoene.perzist.jdbc.db.instance2db;

import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.jdbc.db.Tables;
import ch.hoene.perzist.model.Product;
import ch.hoene.perzist.source.relational.Table;

import java.util.ArrayList;
import java.util.List;

public class Product2Db implements Mapping<Table, List<Object>, Product> {

    public Table get() {
        return Tables.PRODUCTS;
    }

    public List<Object> create(Product product) {
        final List<Object> values = new ArrayList<Object>();

        values.add(product.getId());
        values.add(product.getDescription());
        values.add(product.getCatalog());

        return values;
    }
}