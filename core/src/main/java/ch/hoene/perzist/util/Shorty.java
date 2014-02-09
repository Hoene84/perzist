/**
 * @author Simon Honegger (Hoene84)
 */

package ch.hoene.perzist.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Shorty
{
    public static boolean isVoid(String string)
    {
        return string == null || string.length() == 0;
    }

    public static boolean isVoid(Integer integer)
    {
        return integer == null || integer == 0;
    }

    public static <T extends Collection<?>>  boolean isVoid(T collection)
    {
        return collection == null || collection.size() == 0;
    }

    public static String avoidNull(String s)
    {
        return isVoid(s) ? "" : s;
    }

    public static <T extends Collection<?>> T avoidNull(T collection, T emptyCollections)
    {
        return isVoid(collection) ? emptyCollections : collection;
    }

    public static <E> Set<E> oneElementSet(E element)
    {
        final Set<E> result = new HashSet<E>();
        result.add(element);
        return result;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> List<T> concat(List<T>... lists)
    {
        List<T> result = new ArrayList<T>();
        for(List<T> list : lists)
        {
            result.addAll(list);
        }

        return result;
    }

    public static <T> List<T> concatWith(List<T> arrays, T... elements)
    {
        return concat(arrays, Arrays.asList(elements));
    }
}
