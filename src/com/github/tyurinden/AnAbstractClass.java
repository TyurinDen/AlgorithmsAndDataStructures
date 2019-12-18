package com.github.tyurinden;

public abstract class AnAbstractClass {
    public static final String str = "String from an Abstract Class";

    protected static void m3() {
        System.out.println("Static method m3() from AnAbstractClass");
    };

    protected static void m3(int i) {
        System.out.println("Static method m3(int) from AnAbstractClass");
    };

    private void m3(long l) {

    }
}
