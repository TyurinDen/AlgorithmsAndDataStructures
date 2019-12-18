package com.github.tyurinden;

public interface AnInterface {
    String s = "String from AnInterface";

    default void m() {
        System.out.println("Output from the default method for AnInterface");
    }

    void m1();

}
