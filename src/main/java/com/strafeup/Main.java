package com.strafeup;

public class Main {
    public static void main(String[] args) {
        HashMapOpenAddressing map = new HashMapOpenAddressing();
        map.put(1, 10);
        map.put(5, 20);
        map.put(10, 30);
        map.put(15, 40);
        map.put(20, 50);
        map.put(25, 60);
        map.put(30, 70);
        map.put(35, 80);
        map.get(6);
    }
}
