package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.query.OperationInsert;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Insert;
import ch.hoene.perzist.source.sql.query.MultiValue;
import ch.hoene.perzist.source.sql.query.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        final Sql sql = new Insert(mapping.get(), new MultiValue(mapping.create(instance)));
        return openStatement(db, new JdbcCallback<Integer>() {
            @Override
            public Integer connected(PreparedStatement stmt) throws SQLException {
                for(int i = 0; i < sql.getParams().size(); i++)
                {
                    stmt.setObject(i + 1, sql.getParams().get(i));
                }
                return stmt.executeUpdate();
            }
        }, sql);
    }
}
