package ch.hoene.perzist.source.sql.query;

import ch.hoene.perzist.source.relational.Field;

import java.util.Arrays;
import java.util.List;



public class WhereClauseField implements WhereClausePart
{

    final Field field;
    final Object value;
    final SqlOperator op;

    public WhereClauseField(Field field,
                                    Object value,
                                    SqlOperator op)
    {
        this.field = field;
        this.value = value;
        this.op = op;
    }

    public String toSql()
    {
        return " " + field.getName() + op + " ? ";
    }

    public List<Object> getParams()
    {
        return Arrays.asList(value);
    }
}
