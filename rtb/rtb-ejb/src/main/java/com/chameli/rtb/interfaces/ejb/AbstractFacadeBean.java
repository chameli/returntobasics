package com.chameli.rtb.interfaces.ejb;

import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class AbstractFacadeBean {

    private Injector injector;

    protected <T> T getService(Class<T> clazz) {
        if (injector == null) {
            injector = Guice.createInjector(new RtbServiceModule());
        }
        return injector.getInstance(clazz);
    }

}
