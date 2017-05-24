package com.chameli.rtb.service.junit.dao.beforeafter;

public interface BeforeAfter<T extends BeforeAfterContext> {

    void before(T ctx);

    void after(T ctx);

}
