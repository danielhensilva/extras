package com.bloom.challenge.mathsapi.models;

import java.util.ArrayList;
import java.util.List;

public class PagedMathematicians {
    private List<Mathematician> content = new ArrayList<>();
    private float number;
    private float size;
    private boolean first;
    private boolean last;
    private float totalPages;
    private float totalElements;

    public List<Mathematician> getContent() {
        return content;
    }

    public void setContent(List<Mathematician> content) {
        this.content = content;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public float getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(float totalPages) {
        this.totalPages = totalPages;
    }

    public float getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(float totalElements) {
        this.totalElements = totalElements;
    }
}
