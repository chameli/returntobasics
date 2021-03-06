package com.chameli.rtb.test.common.fw.guice.beforeafter;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Guicer implements BeforeAfter<GuicerBeforeAfterContext> {

    private final List<Module> modules;

    private final Object target;

    public Guicer(Class<? extends Module>[] classes, Object target) {
        this.modules = getModules(classes);
        this.target = target;
    }

    private List<Module> getModules(Class<? extends Module>[] classes) {
        try {
            List<Module> list = new ArrayList<>();
            for (Class<? extends Module> moduleClazz : classes) {
                list.add(moduleClazz.newInstance());
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Module[] createModules(final GuicerBeforeAfterContext ctx) {
        List<Module> list = new ArrayList<>(modules);
        Module module = new AbstractModule() {
            @Override
            protected void configure() {
                bind(EntityManager.class).toInstance(ctx.getEntityManager());
            }
        };
        list.add(module);
        return list.toArray(new Module[0]);
    }

    @Override
    public void before(GuicerBeforeAfterContext ctx) {
        Guice.createInjector(createModules(ctx)).injectMembers(target);
    }

    @Override
    public void after(GuicerBeforeAfterContext ctx) {
    }

}
