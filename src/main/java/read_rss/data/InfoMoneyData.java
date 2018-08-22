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

public class InfoMoneyData {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String InfoMoney (String tipo, String link) throws IOException, IllegalArgumentException, FeedException {
		
		String status = null;
		
		JSONArray list_im 	= new JSONArray();
		URL url2			= new URL(link);
		XmlReader r2 		= null;
				
		try {    		
			r2 			= new XmlReader(url2);
			SyndFeed feed 	= new SyndFeedInput().build(r2);    		
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();				
				JSONObject tec1 	= new JSONObject();
				tec1.put("title", entry.getTitle());
				tec1.put("description", entry.getDescription().getValue());				
				list_im.add(tec1);								
				try (FileWriter file = new FileWriter("c:\\Users\\vitor\\Documents\\GetData\\Infomoney\\" + tipo + ".json")) {	    		
		    		file.write(list_im.toJSONString());
		    		file.flush();
		    		status = "ok - infomoney";
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}    		
		} finally {
			if(r2 != null)
				r2.close();
		}
		
		System.out.println(status);
		
		return status;
		
	}
	
}
