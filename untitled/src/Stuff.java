public class Stuff {
    private static final double x = 0.8;
    int n;
    private String s = "Not Set";

    public Stuff(int n) {
        this.n = n / 5;
    }

    public void ChangeString(String s) {
        s = "Revised";
        System.out.println(s);
    }

    public void doSomething(double d) {
        n = (int) d * 2;
        System.out.println(this);
        n = (int) doSomeMore();
    }

    private double doSomeMore() {
        double d = n * x;
        System.out.println(d);
        return d;
    }

    public String toString() {
        return "Stuff has " + n + " and " + s;
    }
}