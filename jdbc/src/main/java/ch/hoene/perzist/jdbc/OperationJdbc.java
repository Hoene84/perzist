package ch.hoene.perzist.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OperationJdbc {

    protected void openStatement(final Connection db, JdbcCallback callback)
    {
        Statement stmt = null;
        try
        {
            stmt = db.createStatement();
            callback.connected(stmt);
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

    protected interface JdbcCallback
    {
        public void connected(Statement stmt) throws SQLException;
    }

}
