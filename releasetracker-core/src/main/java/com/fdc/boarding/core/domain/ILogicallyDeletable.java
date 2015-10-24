package com.fdc.boarding.core.domain;

import org.joda.time.DateTime;

public interface ILogicallyDeletable {
    /**
     * Retrieve the logical deleted timestamp.
     *
     * @return
     */
    public DateTime getLogicallyDeletedTimestamp(
    );

    /**
     * @return
     *      True if the entity is logically deleted.
     */
    public boolean isLogicallyDeleted(
    );

    /**
     * Set the timestamp for the logical deletion.
     *
     * @param timestamp
     */
    public void setLogicallyDeletedTimestamp(
      DateTime                          timestamp
    );

}
