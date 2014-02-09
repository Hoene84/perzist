package ch.hoene.perzist.source.sql;

import ch.hoene.perzist.framework.creator.CreatorForList;
import ch.hoene.perzist.framework.mapping.Mapping;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.query.Select;

import java.util.List;



public class MappingTable<TARGET, RESULT, C> implements Mapping<Select, List<RESULT>, C>
{
    private final Table table;
    private final CreatorForList<TARGET, RESULT, C, C> creator;

    public MappingTable(Table table, CreatorForList<TARGET, RESULT, C, C> creator)
    {
        this.table = table;
        this.creator = creator;
    }

    public Select get()
    {
        return new Select(table);
    }

    public List<RESULT> create(C queryResult)
    {
        return creator.create(queryResult);
    }
}
