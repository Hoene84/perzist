package ch.hoene.perzist.source.relational;

import java.io.Serializable;
import java.util.List;
import java.util.Set;



public interface View extends Serializable
{
    public Set<? extends Table> getTables();

    public List<Field> getFields();
}
