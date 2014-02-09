package ch.hoene.perzist.framework.mapping;

import ch.hoene.perzist.framework.creator.Creator;

/**
 * @author Simon Honegger (Hoene84)
 *
 * Knows how to create TO's from FROM's  which are dependent from Q
 * Eg: Knows How to create an Instance TO from Query result Cursor FROM from Sql Q
 *
 * @param <Q> eg sql String
 * @param <TO> resulting instance
 * @param <FROM> eg cursor
 */
public interface Mapping<Q, TO, FROM> extends Creator<TO, FROM>, QueryGenerator<Q>
{
	Q get();
	TO create(FROM queryResult);
}
