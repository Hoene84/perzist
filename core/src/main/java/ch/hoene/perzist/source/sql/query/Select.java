package ch.hoene.perzist.source.sql.query;

import ch.hoene.perzist.source.relational.Field;
import ch.hoene.perzist.source.relational.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Select implements Sql
{
    private final String sql;
    private WhereClause whereClause;
    private OrderClause orderClause;
    private Limit limit;

    public enum SelectMethod{
        NORMAL,
        COUNT,
        DISTINCT
    }

    public Select(View view)
    {
        this.sql = "SELECT * FROM " + getTableList(view);
    }

    public Select(View view, Field... fields)
    {
        this(view, SelectMethod.NORMAL, fields);
    }

    public Select(View view, SelectMethod selectMethod, Field... fields)
    {
        this.sql = "SELECT " +
                  getFieldsList(selectMethod, fields) +
                  " FROM " + getTableList(view);
    }

    private String getFieldsList(SelectMethod selectMethod, Field... fields)
    {
        switch (selectMethod)
        {
            case NORMAL:
                return new FieldsPart(Arrays.asList(fields)).toSql();
            case COUNT:
                return " COUNT(" + new FieldsPart(Arrays.asList(fields)).toSql() + ")";
            case DISTINCT:
                return " DISTINCT " + new FieldsPart(Arrays.asList(fields)).toSql() + " ";
            default:
                throw new RuntimeException("Implement SelectMethod " + selectMethod.name());
        }
    }

    private String getTableList(View view)
    {
        return new TablesPart(view.getTables()).toSql();
    }

    public String toSql()
    {
        return sql
                  + (whereClause != null ? " WHERE " + whereClause.toSql() : "")
                  + (orderClause != null ? " ORDER BY "  + orderClause.toSql() : "")
                  + (limit != null ? limit.toSql() : "");
    }

    public List<Object> getParams()
    {
        List<Object> params = new ArrayList<Object>();
        params.addAll(whereClause != null ? whereClause.getParams() : new ArrayList<Object>());
        return params;
    }

    public void setWhereClause(WhereClause whereClause)
    {
        this.whereClause = whereClause;
    }

    public void setOrderClause(OrderClause orderClause)
    {
        this.orderClause = orderClause;
    }

    public void setLimit(Limit limit){
        this.limit = limit;
    }
}
