package read_rss.data;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class TradingViewData {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String TradingView (String papel) throws IOException, IllegalArgumentException, FeedException {
		
		String status = null;
		
    	JSONArray list 		= new JSONArray();
    	URL url1 			= new URL("https://br.tradingview.com/feed/?symbol=" + papel);
    	XmlReader reader 	= null;
    	
    	try {    		
    		reader 			= new XmlReader(url1);
    		SyndFeed feed 	= new SyndFeedInput().build(reader);    		
    		for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();				
				JSONObject tec 	= new JSONObject();
				tec.put("title", entry.getTitle());
				tec.put("description", entry.getDescription().getValue());				
				list.add(tec);								
				try (FileWriter file = new FileWriter("c:\\Users\\vitor\\Documents\\GetData\\TradingView\\" + papel + ".json")) {	    		
		    		file.write(list.toJSONString());
		    		file.flush();
		    		status = "ok";
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}    		
    	} finally {
			if(reader != null)
				reader.close();
		}
    	
    	System.out.println(status);
		
		return status;
		
	}

}
