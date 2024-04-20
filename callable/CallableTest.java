package callable;

import java.util.concurrent.Callable;

public class CallableTest {
    public static void main(String[] args) {
        var out = "aaa";
        Callable<String> cb = () -> out;
        String s;
        try {
            s = cb.call();
            System.out.println(s);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
