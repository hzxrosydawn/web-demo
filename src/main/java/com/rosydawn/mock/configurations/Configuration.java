package com.rosydawn.mock.configurations;

/**
 * Created by vincent on 2017-10-10.
 */
public interface Configuration {
    /**
     * Getter method to get the SQL query to execute.
     *
     * @return
     */
    String getSQL( String sqlString );
}
