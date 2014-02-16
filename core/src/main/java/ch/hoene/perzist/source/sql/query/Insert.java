package ch.hoene.perzist.source.sql.query;

import ch.hoene.perzist.source.relational.Table;

import java.util.List;


public class Insert implements Sql
{
    private final String sql;
    private final List<Object> params;

    public Insert(Table table, MultiValue values)
    {
        this.sql = "INSERT INTO " + table.getName() + " VALUES " + values.toSql();
        this.params = values.getParams();
    }

    public String toSql()
    {
        return sql;
    }

    public List<Object> getParams()
    {
        return params;
    }
}
