package ch.hoene.perzist.source.sql;

import ch.hoene.perzist.access.creator.CreatorForList;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.source.relational.Field;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.query.Select;

import java.util.List;



public class MappingDistinct<TARGET extends View, PROJECTION extends View, RESULT, C> implements Mapping<Select, List<RESULT>, C>
{
	private final PROJECTION view;
	private final List<Field> fields;
	private final CreatorForList<? super TARGET, RESULT, C, C> creator;

	public MappingDistinct(PROJECTION view,
								  CreatorForList<? super TARGET, RESULT, C, C> creator,
								  TARGET target)
	{
		this.view = view;
		this.fields = target.getFields();
      this.creator = creator;
	}

	public Select get()
	{
		return new Select(view, Select.SelectMethod.DISTINCT, fields.toArray(new Field[fields.size()]));
	}

    public List<RESULT> create(C queryResult)
    {
        return creator.create(queryResult);
    }
}
