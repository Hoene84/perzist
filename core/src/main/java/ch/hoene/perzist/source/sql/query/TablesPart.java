package ch.hoene.perzist.source.sql.query;

import ch.hoene.perzist.source.relational.Table;

import java.util.Set;



public class TablesPart implements SqlFragment
{
    private final Set<? extends Table> tables;

    public TablesPart(Set<? extends Table> tables)
    {
        this.tables = tables;
    }

    public String toSql()
    {
        String[] tableNames = new String[tables.size()];
        int i = 0;
        for(Table table : tables)
        {
            tableNames[i++] = table.getName();
        }
        return Helper.getSeparatedList(", ", tableNames);
    }
}
