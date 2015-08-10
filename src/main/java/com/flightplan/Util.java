package com.flightplan;

import java.util.Collection;

public class Util {

    public static final String ROUTE_SEPARATOR = "->";

    private Util() {
        super();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object object) {
        return object == null;
    }
}
