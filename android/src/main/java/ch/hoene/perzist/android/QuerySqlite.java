package ch.hoene.perzist.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ch.hoene.perzist.access.filter.FieldFilter;
import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.filter.FilterSet;
import ch.hoene.perzist.access.filter.FilterVisitorGenerified;
import ch.hoene.perzist.access.filter.NotFilter;
import ch.hoene.perzist.access.filter.Operator;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.query.Query;
import ch.hoene.perzist.access.sort.FieldOrder;
import ch.hoene.perzist.access.sort.Order;
import ch.hoene.perzist.access.sort.OrderSet;
import ch.hoene.perzist.access.sort.RandomOrder;
import ch.hoene.perzist.source.relational.FieldPersistable;
import ch.hoene.perzist.source.relational.QueryRelational;
import ch.hoene.perzist.source.sql.query.BoolOperator;
import ch.hoene.perzist.source.sql.query.OrderClause;
import ch.hoene.perzist.source.sql.query.OrderClauseFields;
import ch.hoene.perzist.source.sql.query.OrderClausePartField;
import ch.hoene.perzist.source.sql.query.OrderClauseRandom;
import ch.hoene.perzist.source.sql.query.Select;
import ch.hoene.perzist.source.sql.query.SqlOperator;
import ch.hoene.perzist.source.sql.query.WhereClause;
import ch.hoene.perzist.source.sql.query.WhereClauseField;


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
