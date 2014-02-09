package ch.hoene.perzist.source.sql;

import ch.hoene.perzist.framework.mapping.Mapping;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Select;
import ch.hoene.perzist.source.sql.query.Sql;



public abstract class Counter<C> implements Mapping<Select, Integer, C>
{
	private final Table table;

	public Counter(Table table)
	{
		this.table = table;
	}

	public Select get()
	{
		return new Select(table, Select.SelectMethod.COUNT, Sql.FIELD_PSEUDO_STAR);
	}
}
