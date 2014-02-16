package ch.hoene.perzist.source.sql.query;

import java.util.ArrayList;
import java.util.List;


public class MultiValue implements SqlPart {
    final List<Object> values;

    public MultiValue(final List<Object> values) {
        this.values = values;
    }

    @Override
    public List<Object> getParams() {
        return values;
    }

    @Override
    public String toSql() {

        List<Value<Object>> sqlValues = new ArrayList<Value<Object>>();
        for(Object value : values)
        {
            sqlValues.add(new Value<Object>(value));
        }

        return " ( " + Helper.getSeparatedList(",", sqlValues.toArray(new Value<?>[sqlValues.size()])) + " ) ";
    }
}
