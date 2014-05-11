package ch.hoene.perzist.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Sort;

import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.query.Query;
import ch.hoene.perzist.source.relational.QueryRelational;
import ch.hoene.perzist.source.sql.query.Select;
import ch.hoene.perzist.source.sql.query.WhereClause;


public class QueryLucene<PROJECTION, RESULT> extends QueryRelational<org.apache.lucene.search.Query, Filter, Sort, RESULT, Document,PROJECTION, PROJECTION>
{
    public QueryLucene(Mapping<Select, RESULT, IndexSearcher> mapping) {
        super(mapping);
    }

    public QueryLucene(Filter filter, Sort order, Mapping<Select, RESULT, IndexSearcher> mapping) {
        super(filter, order, mapping);
    }
}
