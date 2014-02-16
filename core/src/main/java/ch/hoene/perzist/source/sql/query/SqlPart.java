package ch.hoene.perzist.source.sql.query;

import java.util.List;



public interface SqlPart extends SqlFragment
{
	List<? extends Object> getParams();
}
