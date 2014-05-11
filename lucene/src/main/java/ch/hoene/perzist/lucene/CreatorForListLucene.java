package ch.hoene.perzist.lucene;

import ch.hoene.perzist.access.creator.CreatorForList;
import ch.hoene.perzist.access.creator.ResultCreator;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.Doc;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocs;


/**
 * @param <RESULT> resulting set contains instance I
 */
public class CreatorForListLucene<TARGET, RESULT> extends CreatorForList<TARGET, RESULT, Document, IndexSearcher>
{
    int index = 0;
    
    public CreatorForListLucene(ResultCreator<? super TARGET, RESULT, Document> creator)
    {
        super(creator);
    }

    @Override
    public boolean hasNext(IndexSearcher queryResult)
    {
        return index < queryResult.maxDoc();
    }

    @Override
    public Document next(IndexSearcher queryResult)
    {
        try 
        {
            return queryResult.doc(index++);
        } 
        catch (CorruptIndexException e) 
        {
            throw new RuntimeException(e);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
