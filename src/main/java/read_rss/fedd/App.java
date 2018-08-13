package read_rss.fedd;

import java.net.URL;
import java.util.Iterator;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class App {
	
    public static void main( String[] args ) throws Exception {
    	
    	URL url = new URL("https://br.tradingview.com/feed/?symbol=aapl");
    	
    	XmlReader reader = null;
    	
    	try {
    		
    		reader = new XmlReader(url);
    		SyndFeed feed = new SyndFeedInput().build(reader);
    		System.out.println("Feed title: " + feed.getAuthor());
    		
    		for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();
				System.out.println("Titulo: " + entry.getTitle());
				System.out.println("Descrição: " + entry.getDescription().getValue());
				System.out.println("Data: " + entry.getPublishedDate());
			}
    		
    	} finally {
			if(reader != null)
				reader.close();
		}
    	
    }
    
}
