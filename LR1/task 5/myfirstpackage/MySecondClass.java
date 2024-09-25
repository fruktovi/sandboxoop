package myfirstpackage;
public class MySecondClass {
    private int a;
    private int b;

    public MySecondClass() {
        a = 2;
        b = 4;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int subtraction() {
        return a-b;
    }
}
