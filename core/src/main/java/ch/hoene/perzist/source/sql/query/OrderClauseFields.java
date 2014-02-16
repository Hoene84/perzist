package ch.hoene.perzist.source.sql.query;



public class OrderClauseFields implements OrderClause
{
    private final String sql;

    public OrderClauseFields(OrderClausePart... parts)
    {
        sql = Helper.getSeparatedList(" , ", parts);
    }

    private OrderClauseFields(OrderClause orderClause, OrderClause orderClause2)
    {
        sql = orderClause.toSql() + " , " + orderClause2.toSql();
    }

    public String toSql(){
        return sql;
    }

    public OrderClause apply(OrderClause orderClause)
    {
        return new OrderClauseFields(this, orderClause);
    }
}
