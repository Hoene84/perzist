package ch.hoene.perzist.source.relational;

import ch.hoene.perzist.access.creator.Creator;
import ch.hoene.perzist.source.relational.fieldtype.FieldVisitor;



public abstract class FieldPersistable<RESULT, TYPE> extends Field
{
	private final Creator<TYPE, RESULT> mapping;

	protected FieldPersistable(String name,
										Creator<TYPE, RESULT> mapping)
	{
		super(name);
		this.mapping = mapping;
	}

	public FieldPersistable(FieldPersistable<RESULT, TYPE> fieldPersistable)
	{
		this(fieldPersistable.getName(), fieldPersistable.mapping);
	}

	public TYPE get(RESULT type)
	{
		return mapping.create(type);
	}

	public abstract <R> R accept(FieldVisitor<R, ? extends RESULT> visitor);
}
