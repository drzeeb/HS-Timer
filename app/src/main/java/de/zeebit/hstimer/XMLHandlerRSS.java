package de.zeebit.hstimer;

/**
 * Created by Michael on 16.03.2016.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLHandlerRSS extends DefaultHandler {
    private ItemsRss items;
    private ArrayList<ItemRss> itemList;
    private ItemRss item;
    private boolean titleRSS = false, itemRSS = false, titleItem = false, descriptionItem = false, guidItem = false;

    public ItemsRss getItems(){
        return items;
    }
/*
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/its/sem1/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/its/sem2/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/its/sem3/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/its/sem4/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/kst/sem1/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/kst/sem2/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/kst/sem3/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/kst/sem4/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/kst/sem5/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/kst/sem6/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/kst/sem7/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/win/sem1/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/win/sem2/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/win/sem3/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/win/sem4/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/win/sem5/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/win/sem6/";
    "http://pip1.kst.fh-albsig.de/portal/rss/aushang/win/sem7/";
    "http://pip1.kst.fh-albsig.de/portal/rss/news/analle/";
  */

    // Called when tag starts
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("channel")){
            items = new ItemsRss();
            itemList = new ArrayList<>();
        } else if(qName.equalsIgnoreCase("title") && item==null){
            titleRSS = true;
        } else if(qName.equalsIgnoreCase("item")){
            item = new ItemRss();
            itemRSS = true;
        } else if(qName.equalsIgnoreCase("title") && item!=null){
            titleItem = true;
        } else if(qName.equalsIgnoreCase("description")){
            descriptionItem = true;
        } else if(qName.equalsIgnoreCase("guid")){
            guidItem = true;
        }


    }

    // Called when tag closing
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(qName.equalsIgnoreCase("item")){
            itemList.add(item);
        }
        if(qName.equalsIgnoreCase("channel")){
            items.setItemRssArrayList(itemList);
        }
    }

    // Called to get tag characters
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(titleRSS==true){
            items.setRssCourse(new String(ch, start, length));
            titleRSS = false;
        }
        if(titleItem==true){
            item.setTitle(new String(ch, start, length));
            titleItem = false;
        }
        if(descriptionItem==true){
            item.setDescription(new String(ch, start, length));
            descriptionItem = false;
        }
        if(guidItem==true){
            item.setGuid(new String(ch, start, length));
            guidItem = false;
        }


    }

}
