package ch.hoene.perzist.framework.query;



public interface OperationDelete<D, T>
{
	int delete(D db, T target);
}
