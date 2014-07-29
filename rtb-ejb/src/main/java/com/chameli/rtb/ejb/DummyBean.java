package com.chameli.rtb.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class DummyBean {

    public void foo() {
        // It's ok to do nothing
    }
}
