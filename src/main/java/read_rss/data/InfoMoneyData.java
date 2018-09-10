package read_rss.data;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class InfoMoneyData {
	
	final Subs frmt = new Subs();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public String InfoMoney (String tipo, String link) throws IOException, IllegalArgumentException, FeedException {
		
		String status = null;
		
		LocalDate today = LocalDate.now();		
		String fileName = "c:\\Users\\vitor\\Documents\\GetDataset\\Infomoney\\"+today+"\\";		
		Path path 		= Paths.get(fileName);
		
		if(!Files.exists(path)){
			Files.createDirectory(path);
		}
				
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
				tec1.put("description", frmt.unescapeHTML(entry.getDescription().getValue(), 0));				
				list_im.add(tec1);								
				try (FileWriter file = new FileWriter(fileName + tipo + ".json")) {	    		
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
		
//		System.out.println(status);
		
		return status;
		
	}
	
}
