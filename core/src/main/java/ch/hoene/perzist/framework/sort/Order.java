package ch.hoene.perzist.framework.sort;



/**
 * @param <Q> eg sql as String
 */
public interface Order<PROJECTION>
{
	<R> R accept(OrderVisitor<? extends PROJECTION, R> visitor);
}
