package de.zeebit.hstimer;

/**
 * Created by Michael on 16.03.2016.
 */


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandlerMensa extends DefaultHandler {


    //http://android.studicluster.com/tuebingen/api.php?action=getMeals&cid=8
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
