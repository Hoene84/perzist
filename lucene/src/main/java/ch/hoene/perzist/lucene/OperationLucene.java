package ch.hoene.perzist.lucene;

import ch.hoene.perzist.source.sql.query.Sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherManager;

public class OperationLucene {

    protected <R> R openStatement(final SearcherManager db, LuceneCallback<R> callback)
    {
        IndexSearcher searcher = null;
        try
        {
            searcher = db.acquire();
            return callback.connected(searcher);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(searcher != null)
            {
                try {
                    db.release(searcher);
                } catch (IOException e) {
                    //ignore
                }
            }
        }
    }

    protected interface LuceneCallback<R>
    {
        public R connected(IndexSearcher searcher) throws SQLException;
    }

}
