package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.creator.CreatorForList;
import ch.hoene.perzist.access.creator.ResultCreator;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @param <RESULT> resulting set contains instance I
 */
public class CreatorForListJdbc<TARGET, RESULT> extends CreatorForList<TARGET, RESULT, ResultSet, ResultSet>
{
    public CreatorForListJdbc(ResultCreator<? super TARGET, RESULT, ResultSet> creator)
    {
        super(creator);
    }

    @Override
    public boolean hasNext(ResultSet queryResult)
    {
        try {
            return !queryResult.isLast() && !queryResult.isAfterLast();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet next(ResultSet queryResult)
    {
        try {
            queryResult.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return queryResult;
    }
}
