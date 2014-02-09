package ch.hoene.perzist.source.relational.fieldtype;

import ch.hoene.perzist.framework.creator.Creator;
import ch.hoene.perzist.source.relational.FieldPersistable;



public class FieldPersistableAsDouble<RESULT> extends FieldPersistable<RESULT, Double>
{
	public FieldPersistableAsDouble(String name,
												  Creator<Double, RESULT> mapping)
	{
		super(name, mapping);
	}

	public FieldPersistableAsDouble(FieldPersistable<RESULT, Double> fieldPersistable)
	{
		super(fieldPersistable);
	}

	@Override
	public <R> R accept(FieldVisitor<R, ? extends RESULT> visitor)
	{
		return visitor.visit(this);
	}
}
