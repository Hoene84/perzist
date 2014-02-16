package ch.hoene.perzist.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ch.hoene.perzist.access.query.OperationRead;
import ch.hoene.perzist.source.sql.query.Select;


public class ReadOperationSqlLite<I> implements OperationRead<SQLiteDatabase, I>
{
    private final QuerySqlite<?, ?, I> query;

    public ReadOperationSqlLite(QuerySqlite<?, ?, I> query)
    {
        this.query = query;
    }

    @Override
    public I read(SQLiteDatabase db) {


        Cursor cursor = null;
        Select select = query.get();
        try
        {
            String[] params = new String[select.getParams().size()];
            int i = 0;

            for (Object param : select .getParams())
            {
                params[i++] = param.toString();
            }

            cursor = db.rawQuery(select.toSql(), params);

            return query.create(cursor);
        } finally
        {
            if (cursor != null)
            {
                cursor.close();
            }
        }
    }
}
