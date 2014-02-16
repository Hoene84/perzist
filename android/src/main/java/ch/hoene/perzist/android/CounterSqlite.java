package ch.hoene.perzist.android;

import android.database.Cursor;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.Counter;



public class CounterSqlite extends Counter<Cursor>
{
    public CounterSqlite(Table table)
    {
        super(table);
    }

    public Integer create(Cursor queryResult)
    {
        queryResult.moveToFirst();
        return queryResult.getInt(0);
    }
}
