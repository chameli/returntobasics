package com.chameli.rtb.common.sandbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class MyGenericsCloningTest {

    public static class MyTrulyGenericHolder<T extends Cloneable> implements Cloneable {

        private T mutable;

        public MyTrulyGenericHolder(T mutableDate) {
            this.mutable = mutableDate;
        }

        public T getMutable() {
            return mutable;
        }

        /**
         */
        @Override
        public Object clone() throws CloneNotSupportedException {
            MyTrulyGenericHolder<T> clone = (MyTrulyGenericHolder<T>) super.clone();
            // WTF! clone-method is not
            // visible, even though T extends Cloneable!!! Cloning sucks!
            // clone.mutable = (T) mutable.clone();
            return clone;
        }
    }

    public static class MyDateHolder<T extends Date> implements Cloneable {

        private T mutable;

        public MyDateHolder(T mutableDate) {
            this.mutable = mutableDate;
        }

        public T getMutable() {
            return mutable;
        }

        /**
         */
        @Override
        public Object clone() throws CloneNotSupportedException {
            MyDateHolder<T> clone = (MyDateHolder<T>) super.clone();
            clone.mutable = (T) mutable.clone();
            return clone;
        }
    }

    @Test
    public void cloningMyTrulyGenericHolder() throws Exception {
        MyTrulyGenericHolder<Date> instance = new MyTrulyGenericHolder<Date>(new Date());
        MyTrulyGenericHolder<Date> clone = (MyTrulyGenericHolder<Date>) instance.clone();
        assertEquals(instance.getMutable(), clone.getMutable());
        // WTF!!! Can't verify that mutableDate is not same instance, since it's
        // not possible to clone that value if generics is used.
        // assertTrue(instance.getMutable() != clone.getMutable());
    }

    @Test
    public void cloningMyDateHolder() throws Exception {
        MyDateHolder<Date> instance = new MyDateHolder<Date>(new Date());
        MyDateHolder<Date> clone = (MyDateHolder<Date>) instance.clone();
        assertEquals(instance.getMutable(), clone.getMutable());
        assertTrue(instance.getMutable() != clone.getMutable());
    }

}
