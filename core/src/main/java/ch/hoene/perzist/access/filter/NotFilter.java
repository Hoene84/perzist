package ch.hoene.perzist.access.filter;



public class NotFilter<PROJECTION> implements Filter<PROJECTION>
{
	private final Filter<? super PROJECTION> filter;

	public NotFilter(Filter<? super PROJECTION> filter)
	{
		this.filter = filter;
	}

	public <R> R accept(FilterVisitor<? extends PROJECTION, R> visitor)
	{
		return visitor.visit(this);
	}

	public Filter<? super PROJECTION> getFilter()
	{
		return filter;
	}

	@Override
	public String toString()
	{
		return " NOT (" + filter.toString() + ") ";
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof NotFilter)) return false;

		NotFilter notFilter = (NotFilter) o;

		if (filter != null ? !filter.equals(notFilter.filter) : notFilter.filter != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return filter != null ? filter.hashCode() : 0;
	}

}
