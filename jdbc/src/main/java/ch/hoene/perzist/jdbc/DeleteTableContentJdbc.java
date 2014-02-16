package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.query.OperationDelete;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Drop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteTableContentJdbc extends OperationJdbc implements OperationDelete<Connection, Table>
{
    public int delete(Connection db, final Table target)
    {
        return openStatement(db, new JdbcCallback<Integer>() {
            @Override
            public Integer connected(PreparedStatement stmt) throws SQLException {
                return stmt.executeUpdate();
            }
        }, new Drop(target));
    }
}
