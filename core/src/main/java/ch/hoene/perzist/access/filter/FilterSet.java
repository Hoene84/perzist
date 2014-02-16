package ch.hoene.perzist.access.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



public class FilterSet<PROJECTION> implements Filter<PROJECTION>
{
    private final Set<Filter<? super PROJECTION>> filters;

    public FilterSet(Filter<? super PROJECTION>... filter)
    {
        this.filters = new HashSet<Filter<? super PROJECTION>>(Arrays.asList(filter));
    }

    public FilterSet(Set<? extends Filter<? super PROJECTION>> filters)
    {
        this.filters = new HashSet<Filter<? super PROJECTION>>(filters);
    }

    public <R> R accept(FilterVisitor<? extends PROJECTION, R> visitor)
    {
        return visitor.visit(this);
    }

    /**
     * @return never null, Set can be empty
     */
    public Set<Filter<? super PROJECTION>> getFilters()
    {
        return filters;
    }

    public boolean makesObsolete(Filter<?> filter)
    {
        return false;  //Not implemented yet
    }

    @Override
    public String toString()
    {
        return Arrays.deepToString(filters.toArray());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof FilterSet)) return false;

        FilterSet filterSet = (FilterSet) o;

        if (filters != null ? !filters.equals(filterSet.filters) : filterSet.filters != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return filters != null ? filters.hashCode() : 0;
    }
}
