package ch.hoene.perzist.source.sql.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Immutable
 */
public class WhereClause implements WhereClausePart
{
    private List<Object> params = new ArrayList<Object>();
    private final String sql;

    public WhereClause(WhereClauseField part)
    {
        sql = part.toSql();
        params.addAll(part.getParams());
    }

    private WhereClause(String sql, List<Object> params)
    {
        this.sql = sql;
        this.params = params;
    }

    public WhereClause apply(BoolOperator op, WhereClausePart part){

        if(part ==  null)
        {
            return this;
        }
        List<Object> newParams = new ArrayList<Object>(params);
        newParams.addAll(part.getParams());

        String newSql = " (" + sql + ") " + op + " (" + part.toSql() + ") ";
        return new WhereClause(newSql, newParams);
    }

    public String toSql(){
        return sql;
    }

    public List<Object> getParams()
    {
        return Collections.unmodifiableList(params);
    }
}
