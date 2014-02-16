package ch.hoene.perzist.source.sql.query;

import ch.hoene.perzist.source.relational.Field;
import ch.hoene.perzist.source.relational.Table;
import ch.hoene.perzist.source.relational.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Drop implements Sql
{
	private final String sql;

	public Drop(Table table)
	{
		this.sql = "DROP TABLE " + table.getName();
	}

	public String toSql()
	{
		return sql;
	}

	public List<Object> getParams()
	{
		return new ArrayList<Object>();
	}
}
