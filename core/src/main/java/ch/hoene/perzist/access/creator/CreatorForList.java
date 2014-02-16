package ch.hoene.perzist.access.creator;

import java.util.ArrayList;
import java.util.List;



/**
 * @param <RESULT> Instance type
 * @param <S> Source type of row
 * @param <M> Source type of table
 */
public abstract class CreatorForList<TARGET, RESULT, S, M> implements ResultCreator<TARGET, List<RESULT>, M>
{
    private final Creator<? extends RESULT, S> creator;

    protected CreatorForList(Creator<? extends RESULT, S> creator)
    {
        this.creator = creator;
    }

    public List<RESULT> create(M queryResult)
    {
        List<RESULT> result = new ArrayList<RESULT>();

        while(hasNext(queryResult))
        {
            result.add(creator.create(next(queryResult)));
        }

        return result;
    }

    /**
     * @return if the Source has another row to gt with next
     */
    protected abstract boolean hasNext(M queryResult);

    /**
     * @return a Source positioned at next porsition
     */
    protected abstract S next(M queryResult);
}
