public class Main {

    public static void main(String[] args) {
        var map = new CustomHashMap<Integer, String>();

        map.put(1, "hello");
        map.put(2, "world");
        map.put(3, "test");
        map.put(4, "test");
        map.put(5, "test");
        map.put(6, "test");
        map.put(7, "test");
        map.put(8, "test");
        map.put(9, "test");
        map.put(10, "test");
        map.put(11, "test228");
        System.out.println(map.size());


        System.out.println(map);

        map.put(null, null);

        System.out.println(map);

//        map.remove("1");
//        map.remove("2");
//        map.remove("3");
//        map.remove(null);
//
//        System.out.println(map);
//
//        map.put("1", "hello");
//        map.put("2", "world");
//        map.put("3", "test");
//
//        System.out.println(map.get("4"));
//
//        System.out.println(map);


    }
}
