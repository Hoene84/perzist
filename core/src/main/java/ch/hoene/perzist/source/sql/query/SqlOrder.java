package ch.hoene.perzist.source.sql.query;



public enum SqlOrder
{
	ASC(" ASC "),
	DESC(" DESC ");

	final String sql;

	private SqlOrder(String sql)
	{
		this.sql = sql;
	}


	public String toSql()
	{
		return sql;
	}
}
