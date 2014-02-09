package ch.hoene.perzist.access.filter;

import ch.hoene.perzist.source.relational.FieldPersistable;



public abstract class FilterVisitorGenerified<TARGET, RESULT, TYPE, R> implements FilterVisitor<TARGET, R>
{
	public abstract R visit(FieldFilter<TARGET, RESULT, TYPE> fieldFilter,
									FieldPersistable<RESULT, TYPE> field);

	final public <T, Z> R visit(FieldFilter<? super TARGET, Z, T> fieldFilter)
	{
		return visit((FieldFilter<TARGET, RESULT, TYPE>) fieldFilter, (FieldPersistable<RESULT, TYPE>) fieldFilter.getField());
	}
}
