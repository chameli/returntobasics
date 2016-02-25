package com.chameli.rtb.architecture;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareError;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ArchitecturalEnforcement {

    /** Pointcut for finding join points inside the GUI layer */
    @Pointcut("within(*..*web..*)")
    public void withinGui() {
    }

    /** Pointcut for finding method calls to the DAO layer */
    @Pointcut("call(* *..*.dao..*(..))")
    public void callDao() {
    }

    /**
     * Advice that defines an error when a GUI method calls a method in the DAO
     * layer
     */
    @DeclareError("withinGui() && callDao()")
    private static final String GUI_MUST_NOT_USE_DAO = "GUI must not access DAO";
}