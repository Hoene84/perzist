package ch.hoene.perzist.access.paging;

import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.filter.FilterSet;
import ch.hoene.perzist.access.sort.Order;



public abstract class Paging
{

    public static <RESULT, PROJECTION> Filter<? super PROJECTION> getFilter(Filter<? super PROJECTION> oldFilters,
                                        RESULT instance,
                                        Order<? super PROJECTION> order)
    {
        if(instance != null)
        {
            return new FilterSet<PROJECTION>(order.accept(new PagingFilterBuilder<PROJECTION, RESULT>(instance)), oldFilters);
        }
        else
        {
            return oldFilters;
        }
    }
}
