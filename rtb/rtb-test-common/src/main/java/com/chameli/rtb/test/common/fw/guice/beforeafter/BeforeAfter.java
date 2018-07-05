package com.chameli.rtb.test.common.fw.guice.beforeafter;

public interface BeforeAfter<T extends BeforeAfterContext> {

    void before(T ctx);

    void after(T ctx);

}
