package com.chameli.rtb.common.sandbox;

public class MyImmutableMillis {

    /**
     * Marked as static final to make it a constant and with a name in all caps
     * to tell everyone that it is a constant.
     */
    public static final MyImmutableMillis JANUARY_1ST_1970 = new MyImmutableMillis(0);

    /**
     * Marked as final to ensure that this class is immutable, i.e. it should
     * not be possible to change the internal state after it has been
     * instantiated.
     */
    private final long millis;

    public MyImmutableMillis(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return millis;
    }

    /**
     * Returns a new instance since this class it's immutable.
     */
    public MyImmutableMillis add(long millis) {
        return new MyImmutableMillis(this.millis + millis);
    }

}
