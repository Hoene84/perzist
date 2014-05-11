package ch.hoene.perzist.lucene;

import ch.hoene.perzist.access.creator.ResultCreator;
import ch.hoene.perzist.access.db.Database;
import ch.hoene.perzist.access.executor.OperationExecutor;
import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.sort.FieldOrder;
import ch.hoene.perzist.source.relational.FieldSortOrder;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.query.Select;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public abstract class DatabaseLucene implements Database<Select, Connection> {

    final SearcherManager db;

    public DatabaseLucene(String path)
    {
        try {
            Directory dir = FSDirectory.open(new File(path));
            db = new SearcherManager(dir, new SearcherFactory());
        } catch (IOException e) {
            throw new RuntimeException("Not able to get a connetion to: " + path);
        }
    }


    public <TABLE  extends Table> boolean isEmpty(Filter<TABLE> filter, TABLE table)
    {
        return count(filter, table) == 0;
    }

    public <TABLE extends Table> int count(Filter<TABLE> filter, TABLE table)
    {
        return OperationExecutor.execute(
                this,
                new ReadOperationLucene<Integer>(new QueryJdbc<TABLE, TABLE, Integer>(filter, new CounterJdbc(table))));
    }

    public <RESULT, TARGET extends View, PROJECTION extends View, Z> List<RESULT> getList(
            Filter<? super PROJECTION> filter,
            ResultCreator<TARGET, RESULT, ResultSet> creator,
            PROJECTION view,
            TARGET target)
    {
        return getList(filter, creator, view, target, new FieldSortOrder[]{});
    }

    public <RESULT, TARGET extends View, PROJECTION extends View, Z> List<RESULT> getList(
            Filter<? super PROJECTION> filter,
            ResultCreator<TARGET, RESULT, ResultSet> creator,
            PROJECTION view,
            TARGET target,
            FieldSortOrder<? super RESULT, Z>... sortOrders)
    {
        return OperationExecutor.execute(
                this,
                new ReadOperationLucene<List<RESULT>>(
                    new QueryJdbc<PROJECTION, TARGET, List<RESULT>>(
                            filter,
                            FieldOrder.<PROJECTION, RESULT, Z>getMultiFieldOrder(sortOrders),
                            new MappingDistinctJdbc<TARGET, PROJECTION,  RESULT>(view, new CreatorForListLucene<TARGET, RESULT>(creator), target)
                    )
                )
        );
    }

    /**
     * @return inserted Rows
     */
    public <INSTANCE> int push(final INSTANCE instance, Mapping<Table, List<Object>, INSTANCE> instanceToDbMapper)
    {
        return OperationExecutor.execute(this, new InsertOperationJdbc<INSTANCE>(instanceToDbMapper), instance);
    }

    public void drop(final Table table)
    {
        OperationExecutor.execute(this, new DeleteTableContentJdbc(), table);
    }

    public <I> I read(DbReadOp<I, Connection> readOp)
    {
        return readOp.read(db);
    }

    public <I> int write(DbWriteOp<Connection, I> writeOp,
                         I instance)
    {
        return writeOp.write(db, instance);
    }
}
