package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.query.OperationInsert;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Insert;
import ch.hoene.perzist.source.sql.query.MultiValue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class InsertOperationJdbc<I> extends OperationJdbc implements OperationInsert<Connection, I>
{
    private final Mapping<Table, List<Object>, I> mapping;

    public InsertOperationJdbc(Mapping<Table, List<Object>, I> mapping)
    {
        this.mapping = mapping;
    }

    public int insert(final Connection db, final I instance)
    {
        final int[] result = new int[1];
        openStatement(db, new JdbcCallback() {
            @Override
            public void connected(Statement stmt) throws SQLException {
                result[0] =
                        stmt.executeUpdate(new Insert(mapping.get(), new MultiValue(mapping.create(instance))).toSql());
            }
        });
        return result[0];
    }
}
