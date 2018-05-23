package com.chameli.rtb.fw.beforeafter;

public interface BeforeAfter<T extends BeforeAfterContext> {

    void before(T ctx);

    void after(T ctx);

}
