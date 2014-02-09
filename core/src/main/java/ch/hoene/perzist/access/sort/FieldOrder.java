package ch.hoene.perzist.access.sort;

import ch.hoene.perzist.access.filter.FieldFilter;
import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.filter.Operator;
import ch.hoene.perzist.source.relational.FieldPersistable;

import java.util.ArrayList;
import java.util.List;



public class FieldOrder<PROJECTION, RESULT, TYPE> implements Order<PROJECTION>
{
	private final FieldPersistable<? super RESULT, TYPE> field;
	private final SortOrder order;

	public FieldOrder(FieldPersistable<? super RESULT, TYPE> field,
                      SortOrder order)
	{
		this.field = field;
		this.order = order;
	}

	public FieldPersistable<? super RESULT, TYPE> getField()
	{
		return field;
	}

    public SortOrder getOrder()
    {
        return order;
    }

	public <R> R accept(OrderVisitor<? extends PROJECTION, R> visitor)
	{
		return visitor.visit(this);
	}

	@Override
	public String toString()
	{
		return getField().getName() + " " + order;
	}

	public Filter<PROJECTION> asFilter(TYPE value, Operator op){
			return new FieldFilter<PROJECTION, RESULT, TYPE>(field, op, value);
	}

	public static <PROJECTION, RESULT, TYPE> Order<PROJECTION> getMultiFieldOrder(SortOrder order,
												FieldPersistable<? super RESULT, TYPE>... fields)
	{
		List<FieldOrder<PROJECTION, RESULT, TYPE>> orders = new ArrayList<FieldOrder<PROJECTION, RESULT, TYPE>>();
		for(FieldPersistable<? super RESULT, TYPE> field : fields)
		{
			orders.add(new FieldOrder<PROJECTION, RESULT, TYPE>(field, order));
		}
		return new OrderSet<PROJECTION>(orders);
	}
}
