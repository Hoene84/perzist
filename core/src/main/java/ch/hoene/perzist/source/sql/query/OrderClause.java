package ch.hoene.perzist.source.sql.query;



/**
 * Marker Interface
 */
public interface OrderClause extends SqlFragment, OrderClausePart
{
    OrderClause apply(OrderClause orderClause);
}
