package ch.hoene.perzist.access;

import ch.hoene.perzist.source.relational.Field;



public interface UniqueFieldGetter<I>
{
    Field getUniqueFields();
}
