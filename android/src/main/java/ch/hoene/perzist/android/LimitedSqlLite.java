package ch.hoene.perzist.android;

import android.database.Cursor;
import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.Limited;

import java.util.ArrayList;
import java.util.List;



public class LimitedSqlLite<I> extends Limited<I, Cursor> {

    public LimitedSqlLite(Table table, Creator<List<I>, Cursor> creator, int limit) {
        super(table, new FirstSqlLite<java.util.List<I>>(table, creator), limit);
    }
}
