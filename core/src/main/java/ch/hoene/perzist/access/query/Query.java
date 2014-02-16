package ch.hoene.perzist.access.query;

import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.filter.FilterVisitor;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.sort.Order;
import ch.hoene.perzist.access.sort.OrderVisitor;



public abstract class Query<QUERY, WHERE, ORDER, RESULT, CURSOR, TARGET, PROJECTION> implements FilterVisitor<PROJECTION, WHERE>, OrderVisitor<PROJECTION, ORDER>, Mapping<QUERY, RESULT, CURSOR>
{
    private final Filter<? super PROJECTION> filter;
    private final Order<? super PROJECTION> order;

    protected Query()
    {
        this.filter = null;
        order = null;
    }

    protected Query(Filter<? super PROJECTION> filter)
    {
        this.filter = filter;
        order = null;
    }

    protected Query(Order<? super PROJECTION> order)
    {
        this.order = order;
        this.filter = null;
    }

    public Query(Filter<? super PROJECTION> filter, Order<? super PROJECTION> order)
    {
        this.filter = filter;
        this.order = order;
    }

    /**
     * @return can be null
     */
    public Filter<? super PROJECTION> getFilter()
    {
        return filter;
    }

    /**
     * @return can be null
     */
    public Order<? super PROJECTION> getOrder()
    {
        return order;
    }
}
