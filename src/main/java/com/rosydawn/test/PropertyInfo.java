package com.rosydawn.test;

import java.util.Properties;

/**
 * Created by Vincent on 2017/10/10.
 */
public class PropertyInfo {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("操作系统的版本：" + props.getProperty("os.version"));
    }
}
