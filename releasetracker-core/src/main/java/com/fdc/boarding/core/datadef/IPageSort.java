package com.fdc.boarding.core.datadef;

public interface IPageSort extends IEnumDisplayValue
{
    
    public boolean descending(
    );
    
    public String sort(
    );

    public IPageSort[] allValues(
    );
}
