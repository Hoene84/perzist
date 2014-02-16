package ch.hoene.perzist.access.query;



/**
 * @param <D> write target eg SQLiteDb
 * @param <S> Object type that knows how to do the operation
 * @param <I> Object Type of the write information
 */
public interface OperationRead<Q, R, I>
{
    I map(R dbResult);

    Q get();
}
