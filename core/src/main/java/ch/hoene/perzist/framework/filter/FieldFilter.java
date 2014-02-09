package ch.hoene.perzist.framework.filter;

import ch.hoene.perzist.source.relational.FieldPersistable;



public class FieldFilter<PROJECTION, RESULT, TYPE> implements Filter<PROJECTION>
{
	protected final FieldPersistable<? super RESULT, TYPE> field;
	protected final Operator operator;
	protected final TYPE value;

	public FieldFilter(FieldPersistable<? super RESULT, TYPE> field,
							 Operator operator,
							 TYPE value)
	{
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	public FieldPersistable<? super RESULT, TYPE> getField()
	{
		return field;
	}

	public TYPE getValue()
	{
		return value;
	}

	public Operator getOperator()
	{
		return operator;
	}
	public <R> R accept(FilterVisitor<? extends PROJECTION, R> visitor)
	{
		return visitor.visit(this);
	}

	@Override
	public String toString()
	{
		return getField().getName() + " " + operator.name() + " " + value;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof FieldFilter)) return false;

		FieldFilter that = (FieldFilter) o;

		if (!field.equals(that.field)) return false;
		if (operator != that.operator) return false;
		if (value != null ? !value.equals(that.value) : that.value != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = field.hashCode();
		result = 31 * result + operator.hashCode();
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}
}
