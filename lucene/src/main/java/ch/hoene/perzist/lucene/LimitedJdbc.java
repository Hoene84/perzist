package ch.hoene.perzist.lucene;

import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.First;
import ch.hoene.perzist.source.sql.Limited;

import java.sql.ResultSet;
import java.util.List;



public class LimitedJdbc<I> extends Limited<I, ResultSet> {
    public LimitedJdbc(Table table, First<List<I>, ResultSet> first, int limit) {
        super(table, first, limit);
    }
}