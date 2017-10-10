package com.rosydawn.mock.configurations;

/**
 * Created by vincent on 2017-10-10.
 */
public class DefaultConfiguration implements Configuration {
    /**
     * Constructor.
     *
     * @param configurationName
     */
    public DefaultConfiguration(String configurationName) {
    }

    /**
     * Getter method to get the sql that we want to execute.
     *
     * @return
     */
    public String getSQL(String sqlString) {
        return null;
    }
}
