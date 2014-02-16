package ch.hoene.perzist.access.filter;

import java.io.Serializable;



public interface Filter<PROJECTION> extends Serializable
{
    public abstract <R> R accept(FilterVisitor<? extends PROJECTION, R> visitor);
}
