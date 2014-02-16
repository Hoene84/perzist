package ch.hoene.perzist.source.sql.query;

/**
 * @author Simon Honegger (Hoene84)
 */

public class OrderClauseRandom implements OrderClause
{
    public String toSql()
    {
        return " RANDOM() ";
    }

    public OrderClause apply(OrderClause orderClause)
    {
        throw new RuntimeException("random order cant be chained");
    }
}
