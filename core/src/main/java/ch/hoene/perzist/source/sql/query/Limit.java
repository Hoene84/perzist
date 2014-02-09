package ch.hoene.perzist.source.sql.query;



public class Limit implements SqlFragment
{
	final int limit;

	public Limit(int limit)
	{
		this.limit = limit;
	}

	public String toSql()
	{
		return " LIMIT " + limit + " ";
	}
}
