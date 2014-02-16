package ch.hoene.perzist.source.relational;

import ch.hoene.perzist.util.Shorty;

import java.util.Set;



public abstract class Table implements View
{
    final String name;

    public Table(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Set<Table> getTables()
    {
        return Shorty.oneElementSet(this);
    }
}
