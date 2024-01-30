package map;

import java.util.HashMap;
import java.util.Map;

public class MapEntryTest {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(1, 1);
        hm.put(2, 2);
        hm.put(3, 3);

        for (Map.Entry<Integer, Integer> it : hm.entrySet()) {
            System.out.println(it.getKey() + "" + it.getValue());
        }

        hm.merge(null, null, null)

    }

}
