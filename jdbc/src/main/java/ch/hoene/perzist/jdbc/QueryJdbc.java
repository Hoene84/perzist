package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.sort.Order;
import ch.hoene.perzist.source.relational.QueryRelational;
import ch.hoene.perzist.source.sql.query.Select;

import java.sql.ResultSet;


public class QueryJdbc<PROJECTION, TARGET, RESULT> extends QueryRelational<PROJECTION, TARGET, RESULT, ResultSet>
{
    public QueryJdbc(Mapping<Select, RESULT, ResultSet> mapping) {
        super(mapping);
    }

    public QueryJdbc(Filter<? super PROJECTION> filter, Mapping<Select, RESULT, ResultSet> mapping) {
        super(filter, mapping);
    }

    public QueryJdbc(Order<? super PROJECTION> order, Mapping<Select, RESULT, ResultSet> mapping) {
        super(order, mapping);
    }

    public QueryJdbc(Filter<? super PROJECTION> filter, Order<? super PROJECTION> order, Mapping<Select, RESULT, ResultSet> mapping) {
        super(filter, order, mapping);
    }
}
