package com.cloud.web.loadbalance.entity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子正整数
 *
 * @author: heyongliu
 * @date: 2022/1/5
 */
public class AtomicPositiveInteger extends Number {

    private static final long serialVersionUID = -3038533876489105940L;

    private final AtomicInteger i;

    public AtomicPositiveInteger() {
        i = new AtomicInteger();
    }

    public AtomicPositiveInteger(int initialValue) {
        i = new AtomicInteger(initialValue);
    }

    public final int getAndIncrement() {
        for (; ; ) {
            int current = i.get();
            int next = (current >= Integer.MAX_VALUE ? 0 : current + 1);
            if (i.compareAndSet(current, next)) {
                return current;
            }
        }
    }

    public final int getAndDecrement() {
        for (; ; ) {
            int current = i.get();
            int next = (current <= 0 ? Integer.MAX_VALUE : current - 1);
            if (i.compareAndSet(current, next)) {
                return current;
            }
        }
    }

    public final int incrementAndGet() {
        for (; ; ) {
            int current = i.get();
            int next = (current >= Integer.MAX_VALUE ? 0 : current + 1);
            if (i.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public final int decrementAndGet() {
        for (; ; ) {
            int current = i.get();
            int next = (current <= 0 ? Integer.MAX_VALUE : current - 1);
            if (i.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public final int get() {
        return i.get();
    }

    public final void set(int newValue) {
        if (newValue < 0) {
            throw new IllegalArgumentException("new value " + newValue + " < 0");
        }
        i.set(newValue);
    }

    public final int getAndSet(int newValue) {
        if (newValue < 0) {
            throw new IllegalArgumentException("new value " + newValue + " < 0");
        }
        return i.getAndSet(newValue);
    }

    public final int getAndAdd(int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("delta " + delta + " < 0");
        }
        for (; ; ) {
            int current = i.get();
            int next = (current >= Integer.MAX_VALUE - delta + 1 ? delta - 1 : current + delta);
            if (i.compareAndSet(current, next)) {
                return current;
            }
        }
    }

    public final int addAndGet(int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("delta " + delta + " < 0");
        }
        for (; ; ) {
            int current = i.get();
            int next = (current >= Integer.MAX_VALUE - delta + 1 ? delta - 1 : current + delta);
            if (i.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public final boolean compareAndSet(int expect, int update) {
        if (update < 0) {
            throw new IllegalArgumentException("update value " + update + " < 0");
        }
        return i.compareAndSet(expect, update);
    }

    public final boolean weakCompareAndSet(int expect, int update) {
        if (update < 0) {
            throw new IllegalArgumentException("update value " + update + " < 0");
        }
        return i.weakCompareAndSet(expect, update);
    }

    public byte byteValue() {
        return i.byteValue();
    }

    public short shortValue() {
        return i.shortValue();
    }

    public int intValue() {
        return i.intValue();
    }

    public long longValue() {
        return i.longValue();
    }

    public float floatValue() {
        return i.floatValue();
    }

    public double doubleValue() {
        return i.doubleValue();
    }

    public String toString() {
        return i.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + i.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AtomicPositiveInteger)) return false;
        AtomicPositiveInteger other = (AtomicPositiveInteger) obj;
        return i.intValue() == other.i.intValue();
    }
}