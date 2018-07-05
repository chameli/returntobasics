package com.chameli.rtb.common.sandbox;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class MyInheritanceCloningTest {

    public static class MyClass implements Cloneable {

        private Date mutableDate;

        public MyClass(Date mutableDate) {
            this.mutableDate = mutableDate;
        }

        public Date getMutableDate() {
            return mutableDate;
        }

        /**
         * By convention, according to javadoc, see {@link Cloneable} and
         * {@link Object#clone()}, the protected method clone() should be
         * overridden as a public method. This method should also always call
         * super.clone() and all mutable properties should be explicitly copied.
         */
        @Override
        public Object clone() throws CloneNotSupportedException {
            MyClass clone = (MyClass) super.clone();
            clone.mutableDate = (Date) mutableDate.clone();
            return clone;
        }
    }

    public static class MyDerivedClass extends MyClass {
        private String property;

        public MyDerivedClass(Date mutableDate, String property) {
            super(mutableDate);
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }

    @Test
    public void cloningMyClass() throws Exception {
        MyClass instance = new MyClass(new Date());
        MyClass clone = (MyClass) instance.clone();
        assertEquals(instance.getMutableDate(), clone.getMutableDate());
        // Verify that mutableDate is not same instance
        assertNotSame(instance.getMutableDate(), clone.getMutableDate());
    }

    @Test
    public void cloningMyDerivedClass() throws Exception {
        MyDerivedClass instance = new MyDerivedClass(new Date(), "bar");
        MyDerivedClass clone = (MyDerivedClass) instance.clone();
        assertEquals(instance.getMutableDate(), clone.getMutableDate());
        assertEquals(instance.getProperty(), clone.getProperty());
    }
}
