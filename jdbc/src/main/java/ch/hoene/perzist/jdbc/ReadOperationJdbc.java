package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.query.OperationRead;
import ch.hoene.perzist.source.sql.query.Select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
                    stmt.setObject(i + 1, select.getParams().get(i));
                }
                return query.create(stmt.executeQuery());
            }
        }, select);
    }
}
