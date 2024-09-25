class MyFirstClass {
	 public static void main(String[] args){
        MySecondClass o = new MySecondClass();
        System.out.println(o.subtraction());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setA(i);
                o.setB(j);
                System.out.print(o.subtraction());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}


class MySecondClass {
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


