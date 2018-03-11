package com.demir.address.parser.entity;

/**
 * User: murat.demir
 */
public class AddressPiece {

    private String text;
    private Integer begin;
    private Integer end;
    private Integer index;

    public boolean isFirst() {
        boolean result = false;
        if (begin <= 0 || index == 1) {
            result = true;
        }
        return result;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
