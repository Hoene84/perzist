package ch.hoene.perzist.source.sql;

import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Limit;
import ch.hoene.perzist.source.sql.query.Select;

import java.util.List;



public abstract class Limited<I, FROM>  implements Mapping<Select, List<I>, FROM>
{
	private final int limit;
	protected final First<List<I>, FROM> first;

	public Limited(Table table,
            First<List<I>, FROM> first,
						int limit)
	{
		this.limit = limit;
      this.first = first;
	}

	public Select get()
	{
		Select select = first.get();
		select.setLimit(new Limit(limit));
		return select;
	}
}
