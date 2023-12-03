package com.seb.common;

public interface Visitor<T> {

    void accept(T visited);
}
