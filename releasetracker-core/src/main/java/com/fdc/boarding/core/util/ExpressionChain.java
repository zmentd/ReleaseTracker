package com.fdc.boarding.core.util;

import java.util.ArrayList;
import java.util.List;

public class ExpressionChain 
{
    
    /**
     * Create a logical and chain.
     * 
     * @param dflt
     * @return
     */
    public static ExpressionChain init(
      boolean                           init
    )
    {
        ExpressionChain                 chain;
        
        chain       = new ExpressionChain();
        chain.value = init;
        
        return chain;
    }
    
    /**
     * Create a logical and chain.
     * 
     * @param dflt
     * @return
     */
    public static ExpressionChain init(
      boolean                           init
    , String                            message
    )
    {
        ExpressionChain                 chain;
        
        chain       = new ExpressionChain();
        chain.value = init;
        if( init )
        {
            chain.messages.add( message );
        }
        
        return chain;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public static ExpressionChain isNull( 
      Object                           obj
    )
    {
        ExpressionChain                 chain;
        
        chain       = new ExpressionChain();
        chain.value = ( obj == null );
        
        return chain;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public static ExpressionChain isNull( 
      Object                           obj
    , String                           message
    )
    {
        ExpressionChain                 chain;
        
        chain       = new ExpressionChain();
        chain.value = ( obj == null );
        if( ( obj == null ) )
        {
            chain.messages.add( message );
        }
        
        return chain;
    }
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public static ExpressionChain notNull( 
      Object                           obj
    )
    {
        ExpressionChain                 chain;
        
        chain       = new ExpressionChain();
        chain.value = ( obj != null );
        
        return chain;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public static ExpressionChain notNull( 
      Object                           obj
    , String                           message
    )
    {
        ExpressionChain                 chain;
        
        chain       = new ExpressionChain();
        chain.value = ( obj != null );
        if( ( obj != null ) )
        {
            chain.messages.add( message );
        }
       
        return chain;
    }
    
    protected boolean                   value       = true;
    protected List<String>              messages    = new ArrayList<String>();
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain and( 
      boolean                           expression
    )
    {
        value   = value && expression;
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain and( 
      boolean                           expression
    , String                            message
    )
    {
        value   = value && expression;
        if( expression )
        {
            messages.add( message );
        }
       
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain andIsNull( 
      Object                           obj
    )
    {
        value   = value && ( obj == null );
        
        return this;
    }
   
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain andIsNull( 
      Object                           obj
    , String                           message
    )
    {
        value   = value && ( obj == null );
        if( obj == null )
        {
            messages.add( message );
        }
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain andNotNull( 
      Object                           obj
    )
    {
        value   = value && ( obj != null );
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain andNotNull( 
      Object                           obj
    , String                           message
    )
    {
        value   = value && ( obj != null );
        if( obj != null )
        {
            messages.add( message );
        }
        
        return this;
    }
    
    /**
     * @return
     */
    public List<String> messages(
    )
    {
        return messages;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain or( 
      boolean                           expression
    )
    {
        value   = value || expression;
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain or( 
      boolean                           expression
    , String                            message
    )
    {
        value   = value || expression;
        if( expression )
        {
            messages.add( message );
        }
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain orIsNull( 
      Object                           obj
    )
    {
        value   = value || ( obj == null );
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain orIsNull( 
      Object                           obj
    , String                           message
    )
    {
        value   = value || ( obj == null );
        if( obj == null )
        {
            messages.add( message );
        }
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain orNotNull( 
      Object                           obj
    )
    {
        value   = value || ( obj != null );
        
        return this;
    }
    
    /**
     * Evalue against this expression
     * 
     * @param expression
     * @return
     */
    public ExpressionChain orNotNull( 
      Object                           obj
    , String                           message
    )
    {
        value   = value || ( obj != null );
        if( obj != null )
        {
            messages.add( message );
        }
       
        return this;
    }
    
    /**
     * @return
     */
    public boolean result(
    )
    {
        return value;
    }
}
