package ch.hoene.perzist.source.relational;

import ch.hoene.perzist.source.sql.query.OrderClause;
import ch.hoene.perzist.source.sql.query.WhereClause;

/**
 * @author hsi
 */
public interface QueryBuilder
{
    public void setWhereClause(WhereClause whereClause);

    public void setOrderClause(OrderClause orderClause);
    
}
