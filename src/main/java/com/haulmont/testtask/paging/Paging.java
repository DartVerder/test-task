package com.haulmont.testtask.paging;

import java.util.ArrayList;
import java.util.List;

public class Paging {

    private static final int PAGINATION_STEP = 3;

    private boolean nextEnabled;
    private boolean prevEnabled;
    private int pageSize;
    private int pageNumber;

    private List<PageItem> items = new ArrayList<>();

    public Paging() {

    }

    public Paging(boolean nextEnabled, boolean prevEnabled, int pageSize, int pageNumber) {
        this.nextEnabled=nextEnabled;
        this.prevEnabled=prevEnabled;
        this.pageSize=pageSize;
        this.pageNumber=pageNumber;
    }

    public void setNextEnabled(boolean nextEnabled) {
        this.nextEnabled = nextEnabled;
    }

    public boolean isNextEnabled() {
        return nextEnabled;
    }

    public void setPrevEnabled(boolean prevEnabled) {
        this.prevEnabled = prevEnabled;
    }

    public boolean isPrevEnabled() {
        return prevEnabled;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public void addPageItems(int from, int to, int pageNumber) {
        for (int i = from; i < to; i++) {
            PageItem pageItem = new PageItem(PageItemType.PAGE, i, pageNumber != i);
            items.add(pageItem);
        }
    }

    public void last(int pageSize) {
        PageItem pageItem = new PageItem();
        pageItem.setActive(false);
        pageItem.setPageItemType(PageItemType.DOTS);
        items.add(pageItem);

        items.add(new PageItem(PageItemType.PAGE, pageSize, true));
    }

    public void first(int pageNumber) {
        items.add(new PageItem(PageItemType.PAGE, 1, pageNumber != 1));

        PageItem pageItem = new PageItem();
        pageItem.setActive(false);
        pageItem.setPageItemType(PageItemType.DOTS);
        items.add(pageItem);
    }

    public static Paging of(int totalPages, int pageNumber, int pageSize) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setNextEnabled(pageNumber != totalPages);
        paging.setPrevEnabled(pageNumber != 1);
        paging.setPageNumber(pageNumber);

        if (totalPages < PAGINATION_STEP * 2 + 6) {
            paging.addPageItems(1, totalPages + 1, pageNumber);

        } else if (pageNumber < PAGINATION_STEP * 2 + 1) {
            paging.addPageItems(1, PAGINATION_STEP * 2 + 4, pageNumber);
            paging.last(totalPages);

        } else if (pageNumber > totalPages - PAGINATION_STEP * 2) {
            paging.first(pageNumber);
            paging.addPageItems(totalPages - PAGINATION_STEP * 2 - 2, totalPages + 1, pageNumber);

        } else {
            paging.first(pageNumber);
            paging.addPageItems(pageNumber - PAGINATION_STEP, pageNumber + PAGINATION_STEP + 1, pageNumber);
            paging.last(totalPages);
        }

        return paging;
    }
}