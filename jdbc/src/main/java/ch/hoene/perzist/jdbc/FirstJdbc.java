package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.First;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FirstJdbc<I> extends First<I, ResultSet>{

    public FirstJdbc(Table table, Creator<I, ResultSet> creator) {
        super(table, creator);
    }

    public I create(ResultSet queryResult)
    {
        try {
            if(queryResult.first()){
                return creator.create(queryResult);
            }
            else
            {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
