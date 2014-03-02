package ch.hoene.perzist.jdbc.db.db2instance;

import ch.hoene.perzist.access.creator.ResultCreator;
import ch.hoene.perzist.jdbc.db.Tables;
import ch.hoene.perzist.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Db2Product implements ResultCreator<Tables.Products, Product, ResultSet>
{
    @Override
    public Product create(ResultSet resultSet) {
        try {
            return new Product(
                    resultSet.getInt(Tables.Products.ID.getName()),
                    resultSet.getString(Tables.Products.DESC.getName()),
                    resultSet.getString(Tables.Products.CATALOG.getName())
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
