package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.creator.ResultCreator;
import ch.hoene.perzist.access.db.Database;
import ch.hoene.perzist.access.executor.OperationExecutor;
import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.sort.FieldOrder;
import ch.hoene.perzist.access.sort.SortOrder;
import ch.hoene.perzist.source.relational.FieldPersistable;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.query.Select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DatabaseJdbc implements Database<Select, Connection> {

    final Connection db;

    public DatabaseJdbc(String url)
    {
        try {
            db = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Not able to get a connetion to: " + url);
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
                new ReadOperationJdbc<Integer>(new QueryJdbc<TABLE, TABLE, Integer>(filter, new CounterJdbc(table))));
    }

    public <RESULT, TARGET extends View, PROJECTION extends View, Z> List<RESULT> getList(
            Filter<? super PROJECTION> filter,
            ResultCreator<TARGET, RESULT, ResultSet> creator,
            PROJECTION view,
            TARGET target,
            FieldPersistable<? super RESULT, Z>... sortFields)
    {
        return OperationExecutor.execute(
                this,
                new ReadOperationJdbc<List<RESULT>>(
                    new QueryJdbc<PROJECTION, TARGET, List<RESULT>>(
                            filter,
                            FieldOrder.<PROJECTION, RESULT, Z>getMultiFieldOrder(SortOrder.ASC, sortFields),
                            new MappingDistinctJdbc<TARGET, PROJECTION,  RESULT>(view, new CreatorForListJdbc<TARGET, RESULT>(creator), target)
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
