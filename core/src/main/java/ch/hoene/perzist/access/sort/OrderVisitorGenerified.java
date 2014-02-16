package ch.hoene.perzist.access.sort;

import ch.hoene.perzist.source.relational.FieldPersistable;



public abstract class OrderVisitorGenerified<TARGET, RESULT, TYPE, R> implements OrderVisitor<TARGET, R>
{
    public abstract R visit(FieldOrder<TARGET, RESULT, TYPE> fieldOrder,
                                    FieldPersistable<RESULT, TYPE> field);

    final public <T, Z> R visit(FieldOrder<? super TARGET, Z, T> fieldOrder)
    {
        return visit((FieldOrder<TARGET, RESULT, TYPE>)fieldOrder, (FieldPersistable<RESULT, TYPE>)fieldOrder.getField());
    }
}
