package ch.hoene.perzist.access.db;



/**
 * @param <C> eg Cursor
 * @param <Q> eg sql as String
 * @param <D> DB object for write operations
 */
public interface Database<Q, C, D>
{
    abstract <I> I read(Q query, DbReadOp<I, C> readOp);
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
