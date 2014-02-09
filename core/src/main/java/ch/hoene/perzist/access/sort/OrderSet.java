package ch.hoene.perzist.access.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class OrderSet<PROJECTION> implements Order<PROJECTION>
{
	private final List<Order<? super PROJECTION>> orders = new ArrayList<Order<? super PROJECTION>>();

	public OrderSet(Order<? super PROJECTION>... order)
	{
		Collections.addAll(orders, order);
	}

	public OrderSet(List<? extends Order<? super PROJECTION>> orders)
	{
		this.orders.addAll(orders);
	}

	/**
	 * @return never null, Set can be empty
	 */
	public List<Order<? super PROJECTION>> getOrders()
	{
		return orders;
	}

	public <R> R accept(OrderVisitor<? extends PROJECTION, R> visitor)
	{
		return visitor.visit(this);
	}

	public OrderSet<? super PROJECTION> removeLast()
	{
		if(orders.size() > 1)
		{
			return new OrderSet<PROJECTION>(orders.subList(0, orders.size()-1));
		}
		else
		{
			return new OrderSet<PROJECTION>(new ArrayList<Order<? super PROJECTION>>());
		}
	}

	public boolean isEmpty()
	{
		return orders.isEmpty();
	}

	@Override
	public String toString()
	{
		return Arrays.deepToString(orders.toArray());
	}
}
