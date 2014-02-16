package ch.hoene.perzist.source.sql.query;



public abstract class Helper
{
    public static String getSeparatedList(String separator, String... values){

        String result = "";

        for(String value : values)
        {
            result += value + separator;
        }
        return removeLast(result, separator);
    }

    public static String getSeparatedList(String separator, SqlFragment... fragments){

        String result = "";

        for(SqlFragment fragment : fragments)
        {
            result += fragment.toSql() + separator;
        }
        return removeLast(result, separator);
    }

    public static String removeLast(String s, String pattern){
        return s.endsWith(pattern) ?
                  s.substring(0, s.length() - pattern.length()) :
                  s; //cut off last pattern
    }
}
