package ch.hoene.perzist.android;

import android.database.sqlite.SQLiteDatabase;
import ch.hoene.perzist.framework.query.OperationDelete;
import ch.hoene.perzist.source.relational.Table;



public class DeleteTableContentSqlLite implements OperationDelete<SQLiteDatabase, Table>
{
	public int delete(SQLiteDatabase db,
							 Table target)
	{
		return db.delete(target.getName(), null, null);
	}
}
