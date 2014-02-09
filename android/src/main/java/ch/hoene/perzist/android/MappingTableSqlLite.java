package ch.hoene.perzist.android;

import android.database.Cursor;
import ch.hoene.perzist.access.creator.CreatorForList;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.MappingTable;




public class MappingTableSqlLite<TARGET extends View, PROJECTION extends View, RESULT> extends MappingTable<TARGET, PROJECTION, Cursor>  {

    public MappingTableSqlLite(Table table, CreatorForList<TARGET, PROJECTION, Cursor, Cursor> creator) {
        super(table, creator);
    }

}
