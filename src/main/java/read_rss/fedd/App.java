package read_rss.fedd;

import java.awt.List;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class App {
	
    public static void main( String[] args ) throws Exception {
    	
    	String papel 		= "nflx";
    	JSONArray list 		= new JSONArray();
    	URL url 			= new URL("https://br.tradingview.com/feed/?symbol=" + papel);
    	XmlReader reader 	= null;
    	
    	try {
    		
    		reader 			= new XmlReader(url);
    		SyndFeed feed 	= new SyndFeedInput().build(reader);
    		
    		for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();
				
				JSONObject tec = new JSONObject();
								
				tec.put("title", entry.getTitle());
				tec.put("description", entry.getDescription().getValue());				
				
				list.add(tec);
								
				try (FileWriter file = new FileWriter("c:\\Users\\vitor\\Desktop\\" + papel + ".json")) {	    		
		    		file.write(list.toJSONString());
		    		file.flush();				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
    		    		
    	} finally {
			if(reader != null)
				reader.close();
		}
    	
    }
    
}
