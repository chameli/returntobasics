package com.chameli.rtb.common.sandbox;

import java.util.ResourceBundle;

public class MySingleton {

    /**
     * Marked as static to make it a singleton, i.e. only one instance of this
     * class can exist in the JVM.
     */
    private static MySingleton instance;

    /**
     * Marked as final to ensure it's set only once. The final keyword is not
     * completely necessary but it tells you, as a reader of this code, what my
     * intention was with it, i.e. that it should be set only once.
     */
    private final ResourceBundle bundle;

    /**
     * A private default constructor ensures that this class can't be
     * instantiated from elsewhere.
     */
    private MySingleton() {
        bundle = ResourceBundle.getBundle("messages");
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public static MySingleton instance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

}
