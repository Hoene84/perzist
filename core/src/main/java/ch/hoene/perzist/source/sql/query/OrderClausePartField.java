package ch.hoene.perzist.source.sql.query;

import ch.hoene.perzist.framework.sort.SortOrder;
import ch.hoene.perzist.source.relational.Field;



public class OrderClausePartField implements OrderClausePart
{
	final Field field;
	final SqlOrder order;

	public OrderClausePartField(Field field,
										 SortOrder order)
	{
		this.field = field;

		switch (order)
		{
			case ASC:
				this.order = SqlOrder.ASC;
				break;
			case DESC:
				this.order = SqlOrder.DESC;
				break;
			default:
				this.order = SqlOrder.ASC;
		}
	}

	public String toSql()
	{
		return " " + field.getName() + " " + order.toSql() + " ";
	}
}
