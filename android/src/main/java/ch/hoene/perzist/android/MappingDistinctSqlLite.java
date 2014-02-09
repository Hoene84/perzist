package ch.hoene.perzist.android;

import android.database.Cursor;
import ch.hoene.perzist.framework.creator.CreatorForList;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.MappingDistinct;




public class MappingDistinctSqlLite<TARGET extends View, PROJECTION extends View, RESULT> extends MappingDistinct<TARGET, PROJECTION, RESULT, Cursor> {
    public MappingDistinctSqlLite(PROJECTION view, CreatorForList<? super TARGET, RESULT, Cursor, Cursor> creator, TARGET target) {
        super(view, creator, target);
    }
}
