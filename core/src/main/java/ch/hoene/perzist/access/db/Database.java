package ch.hoene.perzist.access.db;



/**
 * @param <Q> eg sql as String
 * @param <D> DB object for write operations
 */
public interface Database<Q, D>
{
    abstract <I> I read(DbReadOp<I, D> readOp);
    abstract <I> int write(DbWriteOp<D, I> writer, I instance);

    interface DbReadOp<I, C>
    {
        public I read(C db);
    }

    interface DbWriteOp<D, I>
    {
        public int write(D target, I instance);
    }
}
