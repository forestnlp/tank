package com.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        return props.get(key) == null ? null : props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(get("init_tank_num"));
    }
}

