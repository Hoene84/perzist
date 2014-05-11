package ch.hoene.perzist.lucene;

import ch.hoene.perzist.access.query.OperationRead;
import ch.hoene.perzist.source.sql.query.Select;
import ch.hoene.perzist.lucene.OperationJdbc;
import ch.hoene.perzist.lucene.QueryJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.lucene.search.IndexSearcher;


public class ReadOperationLucene<I> extends OperationLucene implements OperationRead<Connection, I>
{
    private final QueryLucene<?, I> query;

    public ReadOperationLucene(QueryLucene<?, I> query)
    {
        this.query = query;
    }

    @Override
    public I read(Connection db) {

        final Select select = query.get();
        return openStatement(db, new LuceneCallback<I>() {
            @Override
            public I connected(IndexSearcher searcher) throws SQLException {

                searcher.search(query.get(), );
                return query.create(searcher.search();executeQuery());
            }
        }, select);
    }
}
