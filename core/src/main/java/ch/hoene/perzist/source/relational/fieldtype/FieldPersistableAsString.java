package ch.hoene.perzist.source.relational.fieldtype;

import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.source.relational.FieldPersistable;



public class FieldPersistableAsString<RESULT> extends FieldPersistable<RESULT, String>
{
    public FieldPersistableAsString(String name,
                                                  Creator<String, RESULT> mapping)
    {
        super(name, mapping);
    }

    public FieldPersistableAsString(FieldPersistable<RESULT, String> fieldPersistable)
    {
        super(fieldPersistable);
    }

    @Override
    public <R> R accept(FieldVisitor<R, ? extends RESULT> visitor)
    {
        return visitor.visit(this);
    }
}
