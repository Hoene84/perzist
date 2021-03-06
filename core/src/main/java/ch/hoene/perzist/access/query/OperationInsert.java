package ch.hoene.perzist.access.query;



/**
 * @param <D> write target eg SQLiteDb
 * @param <I> Object Type of the write information
 */
public interface OperationInsert<D, I>
{
    int insert(D db, I instance);
}
