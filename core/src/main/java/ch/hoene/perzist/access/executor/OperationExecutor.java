package ch.hoene.perzist.access.executor;

import ch.hoene.perzist.access.db.Database;
import ch.hoene.perzist.access.query.OperationDelete;
import ch.hoene.perzist.access.query.OperationInsert;
import ch.hoene.perzist.access.query.OperationRead;



public abstract class OperationExecutor
{
    public static <I, Q, C> I execute(Database<Q, C, ?> db, final OperationRead<Q, C, I> operation){
        return db.read(operation.get(), new Database.DbReadOp<I, C>(){
            public I read(C c)
            {
                return operation.map(c);
            }
        });
    }

    public static <D, I> int execute(Database<?, ?, D> db, final OperationInsert<D, I> operation, final I instance){
        return db.write(new Database.DbWriteOp<D, I>()
        {
            public int write(D target,
                                    I instance)
            {
                return operation.insert(target, instance);
            }
        }, instance);
    }

    public static <D, T> void execute(Database<?, ?, D> db, final OperationDelete<D, T> operation, T target){
        db.write(new Database.DbWriteOp<D, T>()
        {
            public int write(D target,
                                    T instance)
            {
                return operation.delete(target, instance);
            }
        }, target);
    }
}
