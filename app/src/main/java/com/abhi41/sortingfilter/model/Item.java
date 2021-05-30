package com.abhi41.sortingfilter.model;

import java.util.Comparator;

public class Item {

    public static Comparator<Item> nameComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<Item> sizeComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getSize() - o2.getSize();
        }
    };

    public static Comparator<Item> priceComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return (int) (o1.getPrize() - o2.getPrize());
        }
    };





    private String name;
    private String color;
    private int size;
    private Double prize;

    public Item(String name, String color, int size, Double prize) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }
}
