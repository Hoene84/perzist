package ch.hoene.perzist.source.relational.fieldtype;

import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.source.relational.FieldPersistable;



public class FieldPersistableAsInteger<RESULT> extends FieldPersistable<RESULT, Integer>
{
    public FieldPersistableAsInteger(String name, Creator<Integer, RESULT> mapping)
    {
        super(name, mapping);
    }

    public FieldPersistableAsInteger(FieldPersistable<RESULT, Integer> fieldPersistable)
    {
        super(fieldPersistable);
    }

    @Override
    public <R> R accept(FieldVisitor<R, ? extends RESULT> visitor)
    {
        return visitor.visit(this);
    }
}
