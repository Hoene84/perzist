package ch.hoene.perzist.android;

import android.database.Cursor;
import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.sort.Order;
import ch.hoene.perzist.source.relational.QueryRelational;
import ch.hoene.perzist.source.sql.query.Select;


public class QuerySqlite<PROJECTION, TARGET, RESULT> extends QueryRelational<PROJECTION, TARGET, RESULT, Cursor>
{
    public QuerySqlite(Mapping<Select, RESULT, Cursor> mapping) {
        super(mapping);
    }

    public QuerySqlite(Filter<? super PROJECTION> filter, Mapping<Select, RESULT, Cursor> mapping) {
        super(filter, mapping);
    }

    public QuerySqlite(Order<? super PROJECTION> order, Mapping<Select, RESULT, Cursor> mapping) {
        super(order, mapping);
    }

    public QuerySqlite(Filter<? super PROJECTION> filter, Order<? super PROJECTION> order, Mapping<Select, RESULT, Cursor> mapping) {
        super(filter, order, mapping);
    }
}
