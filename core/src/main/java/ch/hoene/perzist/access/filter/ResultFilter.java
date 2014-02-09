package ch.hoene.perzist.access.filter;

import java.util.Set;



/**
 * @param <I> eg Instance
 */
public interface ResultFilter<I>
{
	Set<I> apply(Set<I> results);
}
