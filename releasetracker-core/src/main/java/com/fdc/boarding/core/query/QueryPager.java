package com.fdc.boarding.core.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdc.boarding.core.domain.IUniqueIdProvider;
import com.fdc.boarding.core.query.exception.QueryException;

/**
 *  A generic pager for handling the paging of data read from a query service.
 *
 *  The QueryPager will track the current resultset by UniqueId to enable the
 *  client to retrieve the value from a datatable on a screen.
 */
public class QueryPager<T extends IUniqueIdProvider<P>, P extends Serializable, A extends AbstractAp> implements Serializable
{
    private static final long           serialVersionUID    = 1L;

    protected AbstractQuerySvc    		querySvc;
    private int                         pageSize            = 0;
    private int                         totalCount          = 0;
    private int                         pageNumber          = 1;
    private int                         maxPageNumber       = 0;
    private List<Integer>               allPageNumbers      = new ArrayList<Integer>();
    private boolean                     singlePage          = true;
    protected Map<String,Integer>       resultIndexMap;
    protected List<T>                   resultSet;

    /**
     *
     */
    public QueryPager(
    )
    {
        //
        // Default constructor.
        //
        super();
        init();
    }

    /**
     * Perform a search.
     *
     * @param accessPath
     * @return
     * @throws QueryException
     */
    protected Collection<T> find(
      A                                 accessPath
    ) throws QueryException
    {
        int                             firstResult = 0;

        //
        // Clear the index map.
        //
        resultIndexMap.clear();
        if( pageNumber == 1 )
        {
            //
            // First page.
            //
            firstResult     = 0;
        }
        else
        {
            //
            // Not the first page, based off the current pageNumber.
            //
            firstResult     = ( ( pageNumber - 1 ) * pageSize );
        }
        //
        // Get the count of results.
        //
        totalCount      = querySvc.getCount( accessPath );
        if( totalCount > 0 )
        {
            //
            // Results are there, get the data.
            //
            resultSet   = querySvc.find( accessPath, firstResult, pageSize );
            //
            // Load the index map.
            //
            for( int i = 0; i < resultSet.size(); i++ )
            {
                resultIndexMap.put( ( ( P )resultSet.get( i ).getUniqueId() ).toString(), i );
            }
        }
        else
        {
            //
            // No results, clear the resultSet.
            //
            resultSet.clear();
        }
        //
        // Update the page number values.
        //
        updatePageNumbers();

        return resultSet;
    }

    /**
     * Retrieve the first page of results.
     *
     * @param accessPath
     * @return
     * @throws QueryException
     */
    public Collection<T> first(
      A                                 accessPath
    ) throws QueryException
    {
        return search( accessPath );
    }

    /**
     * @return
     *    The allPageNumbers
     */
    public List<Integer> getAllPageNumbers(
    )
    {
        return allPageNumbers;
    }

    /**
     * @return
     *    The maxPageNumber
     */
    public int getMaxPageNumber(
    )
    {
        return maxPageNumber;
    }

    /**
     * @return
     *    The pageNumber
     */
    public int getPageNumber(
    )
    {
        return pageNumber;
    }

    /**
     * @return
     *    The pageSize
     */
    public int getPageSize(
    )
    {
        return pageSize;
    }

    /**
     * @return
     *    The querySvc
     */
    public AbstractQuerySvc getQuerySvc(
    )
    {
        return querySvc;
    }

    /**
     * Get a result from the current page by its key.
     *
     * @param key
     * @return
     */
    public T getResult(
      P                                 key
    )
    {
        int                             index;
        T                               value;

        index   = resultIndexMap.get( key );
        value   = resultSet.get( index );

        return value;
    }

    /**
     * Get a result from the current page by its key.
     *
     * @param key
     * @return
     */
    public T getResult(
      String                            key
    )
    {
        int                             index;
        T                               value;

        index   = resultIndexMap.get( key );
        value   = resultSet.get( index );

        return value;
    }

    /**
     * @return
     *    The resultIndexMap
     */
    public Map<String, Integer> getResultIndexMap(
    )
    {
        return resultIndexMap;
    }

    /**
     * @return
     *    The resultSet
     */
    public List<T> getResultSet(
    )
    {
        return resultSet;
    }

    /**
     * @return
     *    The totalCount
     */
    public int getTotalCount(
    )
    {
        return totalCount;
    }

    /**
     * Determine if another page exists after the current page.
     *
     * @return
     */
    public boolean hasNext(
    )
    {
        return pageNumber < maxPageNumber;
    }

    /**
     * Determine if another page exists before the current page.
     *
     * @return
     */
    public boolean hasPrevious(
    )
    {
        return pageNumber > 1;
    }

    /**
     *
     */
    public void init(
    )
    {
        pageSize        = 20;
        resultSet       = new ArrayList<T>();
        resultIndexMap  = new HashMap<String,Integer>();
    }

    /**
     * @return
     *    The singlePage
     */
    public boolean isSinglePage(
    )
    {
        return singlePage;
    }

    /**
     * Retrieve the last page of results.
     *
     * @param accessPath
     * @return
     * @throws QueryException
     */
    public Collection<T> last(
      A                                 accessPath
    ) throws QueryException
    {
        return search( accessPath , maxPageNumber );
    }

    /**
     * Retrieve the next page of results.
     *
     * @param accessPath
     * @return
     * @throws QueryException
     */
    public Collection<T> next(
      A                                 accessPath
    ) throws QueryException
    {
        if( pageNumber < maxPageNumber )
        {
            pageNumber  = pageNumber + 1;
        }

        return find( accessPath );
    }

    /**
     * Retrieve the previous page of results.
     *
     * @param accessPath
     * @return
     * @throws QueryException
     */
    public Collection<T> previous(
      A                                 accessPath
    ) throws QueryException
    {
        if( pageNumber > 1 )
        {
            pageNumber  = pageNumber - 1;
        }

        return find( accessPath );
    }

    /**
     * Search by AccessPath.
     *
     * @param accessPath
     * @return
     * @throws QueryException
     */
    public Collection<T> search(
      A                                 accessPath
    ) throws QueryException
    {
        pageNumber  = 1;
        return find( accessPath );
    }

    /**
     * Search by page number.
     *
     * @param accessPath
     * @param page
     * @return
     * @throws QueryException
     */
    public Collection<T> search(
      A                                 accessPath
    , int                               page
    ) throws QueryException
    {
        pageNumber  = page;
        return find( accessPath );
    }

    /**
     * @param allPageNumbers
     *    The allPageNumbers to set
     */
    public void setAllPageNumbers(
      List<Integer>                     allPageNumbers
    )
    {
        this.allPageNumbers = allPageNumbers;
    }

    /**
     * @param maxPageNumber
     *    The maxPageNumber to set
     */
    public void setMaxPageNumber(
      int                               maxPageNumber
    )
    {
        this.maxPageNumber = maxPageNumber;
    }

    /**
     * @param pageNumber
     *    The pageNumber to set
     */
    public void setPageNumber(
      int                               pageNumber
    )
    {
        this.pageNumber = pageNumber;
    }

    /**
     * @param pageSize
     *    The pageSize to set
     */
    public void setPageSize(
      int                               pageSize
    )
    {
        this.pageSize = pageSize;
    }

    /**
     * @param querySvc
     *    The querySvc to set
     */
    public void setQuerySvc(
      AbstractQuerySvc	     			querySvc
    )
    {
        this.querySvc = querySvc;
    }

    /**
     * @param resultIndexMap
     *    The resultIndexMap to set
     */
    public void setResultIndexMap(
      Map<String, Integer>              resultIndexMap
    )
    {
        if( resultIndexMap != null )
        {
            this.resultIndexMap = resultIndexMap;
        }
        else
        {
            this.resultIndexMap.clear();
        }
    }

    /**
     * @param resultSet
     *    The resultSet to set
     */
    public void setResultSet(
      List<T>                           resultSet
    )
    {
        this.resultSet = resultSet;
    }

    /**
     * @param singlePage
     *    The singlePage to set
     */
    public void setSinglePage(
      boolean                           singlePage
    )
    {
        this.singlePage = singlePage;
    }

    /**
     * @param totalCount
     *    The totalCount to set
     */
    public void setTotalCount(
      int                               totalCount
    )
    {
        this.totalCount = totalCount;
    }

    /**
     * Determine the total number of pages available.
     * Provides a list of page numbers that can be tied to
     * a screen.
     */
    public void updatePageNumbers(
    )
    {
        int                             maxPages;

        if( totalCount % pageSize == 0 )
        {
            maxPages = totalCount / pageSize;
        }
        else
        {
            maxPages = ( totalCount / pageSize ) + 1;
        }
        maxPageNumber   = maxPages;
        singlePage      = maxPages < 2;
        if( singlePage )
        {
            allPageNumbers.clear();
            allPageNumbers.add( 1 );
        }
        else
        {
            allPageNumbers.clear();
            for( int i = 0; i < maxPageNumber; i++ )
            {
                allPageNumbers.add( i + 1 );
            }
        }
    }
}
