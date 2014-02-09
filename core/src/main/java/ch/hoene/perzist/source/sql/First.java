package ch.hoene.perzist.source.sql;


import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Limit;
import ch.hoene.perzist.source.sql.query.Select;



public abstract class First<I, FROM> implements Mapping<Select, I, FROM>
{
	private final Table table;
	protected final Creator<I, FROM> creator;

	public First(Table table,
					  Creator<I, FROM> creator)
	{
		this.table = table;
		this.creator = creator;
	}

	public Select get()
	{
		Select select = new Select(table);
		select.setLimit(new Limit(1));
		return select;
	}
}
