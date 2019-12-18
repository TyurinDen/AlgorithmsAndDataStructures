package com.github.tyurinden;

import java.io.IOException;

public class Qux {
    private static int anStaticInt = 32; // это будет перенесено в статический блок инициализации
    private int anInt;

    static {
        anStaticInt = 1;
        int i = 0;
        if (true) {
//            throw new RuntimeException();
        }
        if (i == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Exception thrown!");
//                throw e;
            }
        }
    }

    public Qux(int anInt) throws InterruptedException {
        byte[] ints = new byte[1000 * 2 ^ 20];
        Thread.sleep(4000);
        ints = null;
        Thread.sleep(4000);
        long[] longs = new long[1000 * 2 ^ 20];
        Thread.sleep(4000);
        anStaticInt = 3;
        this.anInt = anInt;
        String str = "asd";
//        assert true : "the message for assert false case"; // -ea option for JVM is required
        String str1 = new String(new char[]{'a', 's', 'd'});
        System.out.println(str == str1); // false
        System.out.println(str == str1.intern()); // true
        System.out.println(str1 == str1.intern()); // false
        str1 = str1.intern();
        System.out.println(str == str1); // true

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('a')
                .append('s')
                .append('d');
        String str2 = stringBuilder.toString();
        System.out.println(str == str2); //false
        System.out.println(str == str2.intern()); //true
        System.out.println(str == str2); //false
//        throw new IOException();
    }

    public void m(String str) throws Exception {
        str = "123";
        Qux qux = new Qux(1);
        Corge corge = new Corge();
        System.out.println("anStaticInt = " + anStaticInt);
        System.out.println("str = " + str);
        try {
//                throw new Error();
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception thrown!");
//            throw e;
        }

    }

    public void m1() {
        Corge corge = new Corge();

        System.out.println("anInt = " + anInt);
    }

    public static void main(String[] args) throws InterruptedException {
        int j = 0b01111111_11111111_11111111_11111111;
        Qux qux = new Qux(1);
        Quux quux = new Quux();
        System.out.println("Alarm!! IOException thrown!");
        System.out.println(j);
        System.out.println((j << 1) + 1);
//        throw new IOException();
    }

    class Corge {
        public Corge() {
            anStaticInt++;
            System.out.println("The Corge-object #" + anStaticInt + " is created!");
        }

        public void m(int i, Qux qux) {
            Qux.this.anInt = 1;
            anInt = 4;
            int j = 10;
//            j++;
//            i++;
            class innerCorgeClass {
                public innerCorgeClass() {
                    int k = j;
                    k++;
                    System.out.println("anStaticInt = " + anStaticInt);
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                }

            }
        }

    }

    static class Quux {
        Qux qux;
//        public Quux(Qux qux) {
//            this.qux = qux;
//            qux.anInt = 13;
//        }
    }

}
