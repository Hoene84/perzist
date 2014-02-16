package ch.hoene.perzist.jdbc;

import ch.hoene.perzist.access.filter.FieldFilter;
import ch.hoene.perzist.access.filter.Filter;
import ch.hoene.perzist.access.filter.FilterSet;
import ch.hoene.perzist.access.filter.FilterVisitorGenerified;
import ch.hoene.perzist.access.filter.NotFilter;
import ch.hoene.perzist.access.filter.Operator;
import ch.hoene.perzist.access.mapping.Mapping;
import ch.hoene.perzist.access.query.Query;
import ch.hoene.perzist.access.sort.FieldOrder;
import ch.hoene.perzist.access.sort.Order;
import ch.hoene.perzist.access.sort.OrderSet;
import ch.hoene.perzist.access.sort.RandomOrder;
import ch.hoene.perzist.source.relational.FieldPersistable;
import ch.hoene.perzist.source.sql.query.BoolOperator;
import ch.hoene.perzist.source.sql.query.OrderClause;
import ch.hoene.perzist.source.sql.query.OrderClauseFields;
import ch.hoene.perzist.source.sql.query.OrderClausePartField;
import ch.hoene.perzist.source.sql.query.OrderClauseRandom;
import ch.hoene.perzist.source.sql.query.Select;
import ch.hoene.perzist.source.sql.query.SqlOperator;
import ch.hoene.perzist.source.sql.query.WhereClause;
import ch.hoene.perzist.source.sql.query.WhereClauseField;

import java.sql.ResultSet;


public class QueryJdbc<PROJECTION, TARGET, RESULT> extends Query<Select, WhereClause, OrderClause, RESULT, ResultSet, TARGET, PROJECTION>
{
     private final Mapping<Select, RESULT, ResultSet> mapping;

     public QueryJdbc(Mapping<Select, RESULT, ResultSet> mapping)
     {
          this.mapping = mapping;
     }

     public QueryJdbc(Filter<? super PROJECTION> filter, Mapping<Select, RESULT, ResultSet> mapping)
     {
          super(filter);
          this.mapping = mapping;
     }

     public QueryJdbc(Order<? super PROJECTION> order, Mapping<Select, RESULT, ResultSet> mapping)
     {
          super(order);
          this.mapping = mapping;
     }

     public QueryJdbc(Filter<? super PROJECTION> filter, Order<? super PROJECTION> order, Mapping<Select, RESULT, ResultSet> mapping)
    {
        super(filter, order);

          this.mapping = mapping;
    }

    public Select get()
    {
        Select sql = mapping.get();

        if(getFilter() != null){
            sql.setWhereClause(getFilter().accept(this));
        }

      if(getOrder() != null){
            sql.setOrderClause(getOrder().accept(this));
      }

        return sql;
    }

    public RESULT map(final ResultSet cursor)
    {
        return mapping.create(cursor);
    }

    public <T, Z> WhereClause visit(FieldFilter<? super PROJECTION, Z, T> fieldFilter)
    {
        final SqlOperator sqlOperator;
        switch (fieldFilter.getOperator()){
            case EQ:
                sqlOperator = SqlOperator.EQ;
                break;
            case NEQ:
                sqlOperator = SqlOperator.NEQ;
                break;
            case GE:
                sqlOperator = SqlOperator.GE;
                break;
            case GT:
                sqlOperator = SqlOperator.GT;
                break;
            case LE:
                sqlOperator = SqlOperator.LE;
                break;
            case LIKE:
                sqlOperator = SqlOperator.LIKE;
                break;
            case NOT_LIKE:
                sqlOperator = SqlOperator.NOT_LIKE;
                break;
            case LT:
                sqlOperator = SqlOperator.LT;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return new WhereClause(new WhereClauseField(fieldFilter.getField(), fieldFilter.getValue(), sqlOperator));
    }

    public OrderClause visit(RandomOrder<? super PROJECTION> orderFilter)
    {
        return new OrderClauseRandom();
    }

    public WhereClause visit(FilterSet<? super PROJECTION> filterSet)
    {
        WhereClause whereClause = null;
        for(Filter<? super PROJECTION> filter : filterSet.getFilters()){
            if(whereClause == null)
            {
                whereClause = filter.accept(this);
            }
            else
            {
                whereClause = whereClause.apply(BoolOperator.AND, filter.accept(this));
            }
        }
        return whereClause;
    }


    public WhereClause visit(NotFilter<? super PROJECTION> notFilter)
    {
        Filter<? super PROJECTION> inversedFilter = notFilter.getFilter().accept(new FilterVisitorGenerified<PROJECTION, RESULT,Object,Filter<? super PROJECTION>>()
        {

            @Override
            public Filter<? super PROJECTION> visit(FieldFilter<PROJECTION, RESULT, Object> fieldFilter,
                                                      FieldPersistable<RESULT, Object> field)
            {
                final Operator inversedOp;
                switch (fieldFilter.getOperator())
                {
                    case NEQ:
                        inversedOp = Operator.EQ;
                        break;
                    case EQ:
                        inversedOp = Operator.NEQ;
                        break;
                    case  GE:
                        inversedOp = Operator.LT;
                        break;
                    case  GT:
                        inversedOp = Operator.LE;
                        break;
                    case  LE:
                        inversedOp = Operator.GT;
                        break;
                    case  LIKE:
                        inversedOp = Operator.NOT_LIKE;
                        break;
                    case  NOT_LIKE:
                        inversedOp = Operator.LIKE;
                        break;
                    case  LT:
                        inversedOp = Operator.GE;
                        break;
                    default:
                        throw new RuntimeException("Not supported SqlOperator");
                }
                return new FieldFilter<PROJECTION, RESULT, Object>(fieldFilter.getField(), inversedOp, fieldFilter.getValue());
            }

            public Filter<? super PROJECTION> visit(FilterSet<? super PROJECTION> filterSet)
            {
                for(Filter<? super PROJECTION> f : filterSet.getFilters())
                {
                    f.accept(this);
                }
                return null;
            }

            public Filter<? super PROJECTION> visit(NotFilter<? super PROJECTION> notFilter)
            {
                return notFilter.getFilter();
            }
        });
        return inversedFilter.accept(this);
    }

    public <T, Z> OrderClause visit(FieldOrder<? super PROJECTION, Z, T> fieldOrder)
     {
          return new OrderClauseFields(new OrderClausePartField(fieldOrder.getField(), fieldOrder.getOrder()));
     }

     public OrderClause visit(OrderSet<? super PROJECTION> clauseSet)
     {
          OrderClause orderClause = null;
          for(Order<? super PROJECTION> order : clauseSet.getOrders()){
                if(orderClause == null)
                {
                    orderClause = order.accept(this);
                }
                else
                {
                     orderClause = orderClause.apply(order.accept(this));
                }
          }
          return orderClause;
     }
}
