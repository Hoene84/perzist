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
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.query.Select;

import java.util.List;

/**
 * TURTLE PLAYER
 * <p/>
 * Licensed under MIT & GPL
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * <p/>
 * More Information @ www.turtle-player.co.uk
 *
 * @author Simon Honegger (Hoene84)
 */

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
            TARGET target,
            FieldPersistable<? super RESULT, Z>... sortFields)
    {
        return OperationExecutor.execute(
                this,
                new ReadOperationSqlLite<List<RESULT>>(
                    new QuerySqlite<PROJECTION, TARGET, List<RESULT>>(
                            filter,
                            FieldOrder.<PROJECTION, RESULT, Z>getMultiFieldOrder(SortOrder.ASC, sortFields),
                            new MappingDistinctSqlLite<TARGET, PROJECTION,  RESULT>(view, new CreatorForListSqlite<TARGET, RESULT>(creator), target)
                    )
                )
        );
    }

    /**
     * @return inserted Rows
     */
    public <INSTANCE> int push(final INSTANCE instance, Mapping<Table, ContentValues, INSTANCE> instanceToDbMapper)
    {
        return OperationExecutor.execute(this, new InsertOperationSqlLite<INSTANCE>(instanceToDbMapper), instance);
    }

    public void drop(final Table table)
    {
        OperationExecutor.execute(this, new DeleteTableContentSqlLite(), table);
    }

    public <I> I read(Database.DbReadOp<I, SQLiteDatabase> readOp)
    {
        return readOp.read(db);
    }

    public <I> int write(DbWriteOp<SQLiteDatabase, I> writeOp,
                         I instance)
    {
        return writeOp.write(db, instance);
    }
}
