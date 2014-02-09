package ch.hoene.perzist.framework.sort;



public class RandomOrder<PROJECTION> implements Order<PROJECTION>
{

	public <R> R accept(OrderVisitor<? extends PROJECTION, R> visitor)
	{
		return visitor.visit(this);
	}
}
