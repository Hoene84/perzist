package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.source.sql.query.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperationJdbc {

    protected <R> R openStatement(final Connection db, JdbcCallback<R> callback, Sql sql)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = db.prepareStatement(sql.toSql());
            return callback.connected(stmt);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(stmt != null)
            {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

    protected interface JdbcCallback<R>
    {
        public R connected(PreparedStatement stmt) throws SQLException;
    }

}
