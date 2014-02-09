package ch.hoene.perzist.source.sql.query;



public enum BoolOperator implements SqlFragment
{
	AND(" and "),
	OR(" and ");

	private final String sql;

	private BoolOperator(String sql)
	{
		this.sql = sql;
	}


	public String toSql()
	{
		return sql;
	}
}
