package ch.hoene.perzist.android;

import android.database.Cursor;
import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.First;




public class FirstSqlLite<I> extends First<I, Cursor>{

    public FirstSqlLite(Table table, Creator<I, Cursor> creator) {
        super(table, creator);
    }

    public I create(Cursor queryResult)
    {
        if(queryResult.moveToFirst()){
            return creator.create(queryResult);
        }
        else
        {
            return null;
        }

    }
}
