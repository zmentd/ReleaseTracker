package com.fdc.boarding.core.datadef;

public interface IEnumDisplayValue {
    /**
     * @return
     */
    public String value(
    );

    /**
     * @return
     */
    public String display(
    );
    
    public IEnumDisplayValue locateByDisplay( String value );
}
