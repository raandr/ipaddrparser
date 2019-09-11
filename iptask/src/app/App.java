package app;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        Ipstring ipstr = new Ipstring(
            "123.12.12.12 31.1.1.1 bbb  bbb 21b.b.a.q 1 1 192.168.0.1 2 4 24.2424.242.4 2..4."
            );
        ipstr.printValidIps();
    }
}