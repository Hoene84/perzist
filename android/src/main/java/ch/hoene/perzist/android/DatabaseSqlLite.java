package ch.hoene.perzist.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ch.hoene.perzist.access.creator.ResultCreator;
import ch.hoene.perzist.access.db.Database;
import ch.hoene.perzist.access.executor.OperationExecutor;
import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.sort.FieldOrder;
import ch.hoene.perzist.access.sort.SortOrder;
import ch.hoene.perzist.source.relational.FieldPersistable;
import ch.hoene.perzist.source.relational.FieldSortOrder;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.query.Select;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public abstract class DatabaseSqlLite implements Database<Select, SQLiteDatabase> {

    final SQLiteDatabase db;

    public DatabaseSqlLite(SQLiteOpenHelper sqLiteOpenHelper)
    {
        db = sqLiteOpenHelper.getWritableDatabase();
    }


    protected <TABLE  extends Table> boolean isEmpty(Filter<TABLE> filter, TABLE table)
    {
        return count(filter, table) == 0;
    }

    protected <TABLE extends Table> int count(Filter<TABLE> filter, TABLE table)
    {
        return OperationExecutor.execute(
                this,
                new ReadOperationSqlLite<Integer>(new QuerySqlite<TABLE, TABLE, Integer>(filter, new CounterSqlite(table))));
    }

    protected <RESULT, TARGET extends View, PROJECTION extends View, Z> List<RESULT> getList(
            Filter<? super PROJECTION> filter,
            ResultCreator<TARGET, RESULT, Cursor> creator,
            PROJECTION view,
            TARGET target)
    {
        return getList(filter, creator, view, target, new FieldSortOrder[]{});
    }

    protected <RESULT, TARGET extends View, PROJECTION extends View, Z> List<RESULT> getList(
            Filter<? super PROJECTION> filter,
            ResultCreator<TARGET, RESULT, Cursor> creator,
            PROJECTION view,
            TARGET target,
            FieldSortOrder<? super RESULT, Z>... sortOrders)
    {
        return OperationExecutor.execute(
                this,
                new ReadOperationSqlLite<List<RESULT>>(
                    new QuerySqlite<PROJECTION, TARGET, List<RESULT>>(
                            filter,
                            FieldOrder.<PROJECTION, RESULT, Z>getMultiFieldOrder(sortOrders),
                            new MappingDistinctSqlLite<TARGET, PROJECTION,  RESULT>(view, new CreatorForListSqlite<TARGET, RESULT>(creator), target)
                    )
                )
        );
    }

    public <I> I read(Database.DbReadOp<I, SQLiteDatabase> readOp)
    {
        return readOp.read(db);
    }

    public <I> int write(DbWriteOp<SQLiteDatabase, I> writeOp,
                         I instance)
    {
        throw new UnsupportedOperationException();
    }
}
