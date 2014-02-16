package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.sql.Counter;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CounterJdbc extends Counter<ResultSet>
{
	public CounterJdbc(Table table)
	{
		super(table);
	}

	public Integer create(ResultSet queryResult)
	{
      try {
		    queryResult.first();
        return queryResult.getInt(0);
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
}
