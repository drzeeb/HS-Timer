package de.zeebit.hstimer;

/**
 * Created by Michael on 16.03.2016.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandlerRSS extends DefaultHandler {
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


    }

    // Called when tag closing
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

    }

    // Called to get tag characters
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        // We don't need this, because our values are set as an attribute

    }

}
