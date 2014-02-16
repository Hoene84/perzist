package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.creator.CreatorForList;
import ch.hoene.perzist.source.relational.View;
import ch.hoene.perzist.source.sql.MappingDistinct;

import java.sql.ResultSet;


public class MappingDistinctJdbc<TARGET extends View, PROJECTION extends View, RESULT> extends MappingDistinct<TARGET, PROJECTION, RESULT, ResultSet> {
    public MappingDistinctJdbc(PROJECTION view, CreatorForList<? super TARGET, RESULT, ResultSet, ResultSet> creator, TARGET target) {
        super(view, creator, target);
    }
}
