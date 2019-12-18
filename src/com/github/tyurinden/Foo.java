package com.github.tyurinden;

public class Foo extends AnAbstractClass implements AnInterface2, AnInterface {
    static int j;

    static {
        int[] ints = new int[]{1, 2, 3};
        System.out.println(j);
//        System.out.println(ints[6]);
    }

    {
//        j = 10;
        System.out.println(j++);
    }

    public static final String str = "String from the Foo class";

    public static void m3() {
        System.out.println("Static method m3() from Foo".toUpperCase());
    }

    public void m3(int i, int j) {

    }

    public Foo() {

    }

    public static void main(String[] args) {
        final byte b = 114;
        Integer integer = Integer.valueOf(142);
        int[] ints = new int[]{14};
        final int i = 122;
        Byte bb = (byte) ints[0];
        System.out.println("bb = " + bb);
        System.out.println(integer == (int) bb);
        bb = i;
//        i = bb;
        Foo foo = new Foo();
        AnAbstractClass foo0 = new Foo();
        AnInterface foo1 = new Foo();
        AnInterface2 foo2 = new Foo();
        foo.m1();
        foo.m2();
        Foo.m3();
        foo0.m3(); // выполнится метод AnAbstractClass. Можно, не некорректно.
        AnAbstractClass.m3(); // правильнее вот так
        System.out.println(AnInterface.s);
        System.out.println(str);
        System.out.println(AnAbstractClass.str);
        foo1.m();
        foo2.m();
    }

    @Override
    public void m1() {
        System.out.println("m1");
    }

    @Override
    public void m2() {
        System.out.println("m2");
    }
}
