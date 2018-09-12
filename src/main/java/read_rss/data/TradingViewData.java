package read_rss.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import read_rss.data.Post;

public class TradingViewData {
	
	final Subs frmt = new Subs();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
//	public String TradingView (String papel) throws IOException, IllegalArgumentException, FeedException {
//		
//		String status = null;
//		
//		LocalDate today = LocalDate.now();		
//		String fileName = "c:\\Users\\vitor\\Documents\\GetDataset\\TradingView\\"+today+"\\";		
//		Path path 		= Paths.get(fileName);
//		
//		if(!Files.exists(path)){
//			Files.createDirectory(path);
//		}
//		
//    	JSONArray list 		= new JSONArray();
//    	URL url1 			= new URL("https://br.tradingview.com/feed/?symbol=" + papel);
//    	XmlReader reader 	= null;
//    	    	
//    	try {    		
//    		reader 			= new XmlReader(url1);
//    		SyndFeed feed 	= new SyndFeedInput().build(reader);
//    		
//    		for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {			    			
//    			SyndEntry entry = (SyndEntry) i.next();				
//				JSONObject tec 	= new JSONObject();		
//				tec.put("title", entry.getTitle());
//				tec.put("description", frmt.unescapeHTML(entry.getDescription().getValue(), 0));	
//				tec.put("link", entry.getLink());
//				
//				String url 	= entry.getLink();
//				String t1 	= url.replace("/v/","/chart/"+papel.toUpperCase()+"/");
//            	String t2 	= t1.replace("http", "https");
//            	//System.out.println(t2);
//            	
//            	Document document = Jsoup.connect(t2).followRedirects(false).timeout(6000).get();
//            	if(!document.body().getElementsByClass("tv-idea-label").isEmpty()) {
//            		String value = document.body().getElementsByClass("tv-idea-label").get(0).text();
//            		//System.out.println(value);
//            		tec.put("classe", value);
//            	} else {
//            		tec.put("classe", null);
//            	}
//				
//				list.add(tec);								
//				try (FileWriter file = new FileWriter(fileName + papel + ".json")) {	    		
//		    		file.write(list.toJSONString());
//		    		file.flush();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}				
//			}   
//    		status = "Dados TradingView OK!";
//    	} finally {
//			if(reader != null) {
//				reader.close();
//			}
//		}
//	    	
//    	System.out.println(status);
//    	     
//        return status;			
//		
//	}
	
	public String csvWriter () {
		
		String COMMA_DELIMITER 		= ";";
		String NEW_LINE_SEPARATOR 	= "\n";		
		String FILE_HEADER 			= "title;description;link;classe";		
		String status 				= null;
		
		JSONParser parser = new JSONParser();
		
		try {
		
			Object obj = parser.parse(new FileReader("C:\\Users\\vitor\\Documents\\GetDataset\\TradingView\\2018-09-11\\petr4.json"));
					
			JSONArray ja = (JSONArray) obj;			
			
			for (int i = 0; i < ja.size(); i++) {				
				JSONObject jo 		= (JSONObject) ja.get(i);
				String title 		= (String) jo.get("title");
				String description 	= (String) jo.get("description");
				String classe 		= (String) jo.get("classe");				
			}		
					
		} catch (Exception e) {
			// TODO: handle exception
		}			
		
		return status;
		
	}

}



























