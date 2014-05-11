package ch.hoene.perzist.lucene;

import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.Counter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.search.TopDocs;


public class CounterLucene extends Counter<TopDocs>
{
    public CounterLucene(Table table)
    {
        super(table);
    }

    public Integer create(TopDocs queryResult)
    {
        return queryResult.totalHits;
    }
}
