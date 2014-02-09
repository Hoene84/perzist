package ch.hoene.perzist.source.relational;

import java.io.Serializable;



public class Field implements Serializable
{
	private final String name;

	public Field(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Field field = (Field) o;

		if (!name.equals(field.name)) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
}
