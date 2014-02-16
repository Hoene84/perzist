package ch.hoene.perzist.source.sql.query;



public enum SqlOperator
{
    GT(" > "),
    LT(" < "),
    GE(" >= "),
    LE(" <= "),
    EQ(" = "),
    NEQ(" != "),
    LIKE(" LIKE "),
    NOT_LIKE(" NOT LIKE ");

    final String string;

    private SqlOperator(String op)
    {
        this.string = op;
    }

    @Override
    public String toString()
    {
        return string;
    }
}
