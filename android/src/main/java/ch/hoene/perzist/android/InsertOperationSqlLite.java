package ch.hoene.perzist.android;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.query.OperationInsert;
import ch.hoene.perzist.source.relational.Table;



public class InsertOperationSqlLite<I> implements OperationInsert<SQLiteDatabase, I>
{
    private final Mapping<Table, ContentValues, I> mapping;

    public InsertOperationSqlLite(Mapping<Table, ContentValues, I> mapping)
    {
        this.mapping = mapping;
    }

    public int insert(final SQLiteDatabase db, I instance)
    {
        long newRowId = db.insert(mapping.get().getName(), null, mapping.create(instance));
        return (newRowId < 0) ? 0 : 1;
    }
}
