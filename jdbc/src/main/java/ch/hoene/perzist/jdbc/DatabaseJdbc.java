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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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

public abstract class DatabaseJdbc implements Database<Select, ResultSet, Connection> {

    final Connection db;

    public DatabaseJdbc(String url)
    {
        try {
            db = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Not able to get a connetion to: " + url);
        }
    }


    protected <TABLE  extends Table> boolean isEmpty(Filter<TABLE> filter, TABLE table)
    {
        return count(filter, table) == 0;
    }

    protected <TABLE extends Table> int count(Filter<TABLE> filter, TABLE table)
    {
        return OperationExecutor.execute(
                this,
                new QueryJdbc<TABLE, TABLE, Integer>(filter, new CounterJdbc(table)));
    }

    protected <RESULT, TARGET extends View, PROJECTION extends View, Z> List<RESULT> getList(
            Filter<? super PROJECTION> filter,
            ResultCreator<TARGET, RESULT, ResultSet> creator,
            PROJECTION view,
            TARGET target,
            FieldPersistable<? super RESULT, Z>... sortFields)
    {
        return OperationExecutor.execute(
                this,
                new QueryJdbc<PROJECTION, TARGET, List<RESULT>>(
                        filter,
                        FieldOrder.<PROJECTION, RESULT, Z>getMultiFieldOrder(SortOrder.ASC, sortFields),
                        new MappingDistinctJdbc<TARGET, PROJECTION,  RESULT>(view, new CreatorForListJdbc<TARGET, RESULT>(creator), target)
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

    public <I> I read(Select query,
                      DbReadOp<I, ResultSet> readOp)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = db.prepareStatement(query.toSql());
            for(int i = 0; i < query.getParams().size(); i++)
            {
                stmt.setString(i + 1, query.getParams().get(i).toString());
            }
            return readOp.read(stmt.executeQuery());
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (stmt != null)
            {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

    public <I> int write(DbWriteOp<Connection, I> writeOp,
                         I instance)
    {
        return writeOp.write(db, instance);
    }
}
