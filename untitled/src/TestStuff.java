public class TestStuff {
    public static void main(String[] args) {
        Stuff myStuff = new Stuff(12);
        System.out.println(myStuff);
        myStuff.doSomething(6.4);
        System.out.println(myStuff);
        String s = "Original";
        myStuff.ChangeString(s);
        System.out.println(s);
        System.out.println(myStuff);
        System.out.println(myStuff.n);
    }
}