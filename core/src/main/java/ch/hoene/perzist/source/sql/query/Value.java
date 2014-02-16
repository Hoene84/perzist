package ch.hoene.perzist.source.sql.query;


import java.util.Arrays;
import java.util.List;

public class Value<V> implements SqlPart{

    final V value;

    public Value(final V value) {
        this.value = value;
    }

    @Override
    public List<V> getParams() {
        return Arrays.asList(value);
    }

    @Override
    public String toSql() {
        return " ? ";
    }
}
