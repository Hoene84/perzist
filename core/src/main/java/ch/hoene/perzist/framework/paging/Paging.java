package ch.hoene.perzist.framework.paging;

import ch.hoene.perzist.framework.filter.Filter;
import ch.hoene.perzist.framework.filter.FilterSet;
import ch.hoene.perzist.framework.sort.Order;



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
