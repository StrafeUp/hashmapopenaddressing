package com.strafeup;

public class Main {
    public static void main(String[] args) {
        HashMapOpenAddressing map = new HashMapOpenAddressing();
        map.put(0, 5);
        map.put(1, 10);
        map.put(-1, 100);
        map.put(5, 20);
        map.put(Integer.MAX_VALUE, 2000);
        map.put(Integer.MIN_VALUE, 1000);
        long l = map.get(-1);
        long l1 = map.get(6);
        System.out.println(map.get(Integer.MAX_VALUE));
        System.out.println(map.get(Integer.MIN_VALUE));
        /*map.put(10, 30);
        map.put(15, 40);
        map.put(20, 50);
        map.put(25, 60);
        map.put(30, 70);
        map.put(35, 80);*/

        //map.get(6);
    }
}
