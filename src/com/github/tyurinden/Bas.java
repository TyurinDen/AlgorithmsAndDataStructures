package com.github.tyurinden;

public class Bas extends Bar implements Cloneable {
    private final static int i = 10;
    private int j;
    private final int k;
    private final String s = "string";

    public Bas(int j, int k) {
        this.j = j;
        this.k = k;
    }

    public static void m2 (int i) {

    }

    public void m2 (long i) {

    }

    public static void main(String[] args) {
        Bas bas = new Bas(1, 4);
        System.out.println("bas = " + bas);
        Bas basClone = null;
        try {
            basClone = bas.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("basClone = " + basClone);
    }

    @Override
    protected Bas clone() throws CloneNotSupportedException {
        return (Bas) super.clone();
    }

    @Override
    public String toString() {
        return "Bas{" +
                "j=" + j +
                ", k=" + k +
                '}';
    }
}
