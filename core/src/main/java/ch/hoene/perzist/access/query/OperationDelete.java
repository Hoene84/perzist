package ch.hoene.perzist.access.query;



public interface OperationDelete<D, T>
{
    int delete(D db, T target);
}
