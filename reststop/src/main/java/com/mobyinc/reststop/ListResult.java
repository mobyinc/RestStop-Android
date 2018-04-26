package com.mobyinc.reststop;

public class ListResult<T> {
    public int total;
    public int page;
    public int perPage;
    public T[] items;

    public ListResult(int total, int page, int perPage, T[] items) {
        this.total = total;
        this.page = page;
        this.perPage = perPage;
        this.items = items;
    }

    public T first() {
        if (items == null || items.length == 0) return null;

        return items[0];
    }
}
