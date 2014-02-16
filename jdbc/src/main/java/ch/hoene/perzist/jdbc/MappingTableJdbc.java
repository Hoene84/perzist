package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.creator.CreatorForList;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.MappingTable;

import java.sql.ResultSet;


public class MappingTableJdbc<TARGET extends View, PROJECTION extends View, RESULT> extends MappingTable<TARGET, PROJECTION, ResultSet>  {

    public MappingTableJdbc(Table table, CreatorForList<TARGET, PROJECTION, ResultSet, ResultSet> creator) {
        super(table, creator);
    }

}
