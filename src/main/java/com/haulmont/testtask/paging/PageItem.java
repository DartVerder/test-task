package com.haulmont.testtask.paging;

import java.util.Objects;

public class PageItem {

    private PageItemType pageItemType;

    private int index;

    private boolean active;

    public PageItem() {
    }

    public PageItem(PageItemType pageItemType, int index, boolean active) {
        this.pageItemType = pageItemType;
        this.index = index;
        this.active = active;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PageItemType getPageItemType() {
        return pageItemType;
    }

    public void setPageItemType(PageItemType pageItemType) {
        this.pageItemType = pageItemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageItem pageItem = (PageItem) o;
        return index == pageItem.index &&
                active == pageItem.active &&
                pageItemType == pageItem.pageItemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageItemType, index, active);
    }

    @Override
    public String toString() {
        return "PageItem{" +
                "pageItemType=" + pageItemType +
                ", index=" + index +
                ", active=" + active +
                '}';
    }
}