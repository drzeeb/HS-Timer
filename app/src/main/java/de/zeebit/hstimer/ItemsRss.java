package de.zeebit.hstimer;

import java.util.ArrayList;

/**
 * Created by Michael on 31.03.2016.
 */
public class ItemsRss {
    private ArrayList<ItemRss> itemRssArrayList;
    private String rssCourse;

    public ArrayList<ItemRss> getItemRssArrayList() {
        return itemRssArrayList;
    }

    public void setItemRssArrayList(ArrayList<ItemRss> itemRssArrayList) {
        this.itemRssArrayList = itemRssArrayList;
    }

    public String getRssCourse() {
        return rssCourse;
    }

    public void setRssCourse(String rssCourse) {
        this.rssCourse = rssCourse;
    }
}
