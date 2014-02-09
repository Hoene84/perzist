package ch.hoene.perzist.android;

import android.database.Cursor;
import ch.hoene.perzist.framework.creator.CreatorForList;
import ch.hoene.perzist.framework.creator.ResultCreator;



/**
 * @param <RESULT> resulting set contains instance I
 */
public class CreatorForListSqlite<TARGET, RESULT> extends CreatorForList<TARGET, RESULT, Cursor, Cursor>
{
    public CreatorForListSqlite(ResultCreator<? super TARGET, RESULT, Cursor> creator)
    {
        super(creator);
    }

    @Override
    public boolean hasNext(Cursor queryResult)
    {
        return !queryResult.isLast() && !queryResult.isAfterLast();
    }

    @Override
    public Cursor next(Cursor queryResult)
    {
        queryResult.moveToNext();
        return queryResult;
    }
}
