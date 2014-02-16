package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.query.OperationInsert;
import ch.hoene.perzist.access.query.OperationRead;
import ch.hoene.perzist.access.query.Query;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Insert;
import ch.hoene.perzist.source.sql.query.MultiValue;
import ch.hoene.perzist.source.sql.query.Select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class ReadOperationJdbc<I> extends OperationJdbc implements OperationRead<Connection, I>
{
    private final QueryJdbc<?, ?, I> query;

    public ReadOperationJdbc(QueryJdbc<?, ?, I> query)
    {
        this.query = query;
    }

    @Override
    public I read(Connection db) {

        final Select select = query.get();
        return openStatement(db, new JdbcCallback<I>() {
            @Override
            public I connected(PreparedStatement stmt) throws SQLException {
                for(int i = 0; i < select.getParams().size(); i++)
                {
                    stmt.setString(i + 1, select.getParams().get(i).toString());
                }
                return query.create(stmt.executeQuery());
            }
        }, select);
    }
}
