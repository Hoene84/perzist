package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.query.OperationDelete;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Drop;
import ch.hoene.perzist.source.sql.query.Insert;
import ch.hoene.perzist.source.sql.query.MultiValue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DeleteTableContentJdbc extends OperationJdbc implements OperationDelete<Connection, Table>
{
    public int delete(Connection db, final Table target)
    {
        final int[] result = new int[1];
        openStatement(db, new JdbcCallback() {
            @Override
            public void connected(Statement stmt) throws SQLException {
                result[0] = stmt.executeUpdate(new Drop(target).toSql());
            }
        });
        return result[0];
    }
}
