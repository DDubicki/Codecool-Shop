package com.codecool.shop.order;

import java.util.HashMap;

public class Order {

    private static final HashMap<Integer, Integer> data = new HashMap();

    public static void setData(int productId) {
        if (data.containsKey(productId)) {
            int quantity = data.get(productId) + 1;
            data.replace(productId, quantity);
        } else {
            data.put(productId, 1);
        }
    }

    public HashMap<Integer, Integer> getData() {
        return data;
    }
}
