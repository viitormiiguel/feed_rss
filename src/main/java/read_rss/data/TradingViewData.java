package read_rss.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

import javax.swing.plaf.FileChooserUI;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.CDL;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.apache.commons.io.FileUtils;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class TradingViewData {
	
	final Subs frmt = new Subs();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public String TradingView (String papel) throws IOException, IllegalArgumentException, FeedException {
		
		String status = null;
		
		LocalDate today = LocalDate.now();		
		String fileName = "c:\\Users\\vitor\\Documents\\GetDataset\\TradingView\\"+today+"\\";		
		Path path 		= Paths.get(fileName);
		
		if(!Files.exists(path)){
			Files.createDirectory(path);
		}
		
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
				tec.put("description", frmt.unescapeHTML(entry.getDescription().getValue(), 0));	
				tec.put("link", entry.getLink());
				
				String url 	= entry.getLink();
				String t1 	= url.replace("/v/","/chart/"+papel.toUpperCase()+"/");
            	String t2 	= t1.replace("http", "https");
            	//System.out.println(t2);
            	
            	Document document = Jsoup.connect(t2).followRedirects(false).timeout(6000).get();
            	if(!document.body().getElementsByClass("tv-idea-label").isEmpty()) {
            		String value = document.body().getElementsByClass("tv-idea-label").get(0).text();
            		//System.out.println(value);
            		tec.put("classe", value);
            	} else {
            		tec.put("classe", null);
            	}
				
				list.add(tec);								
				try (FileWriter file = new FileWriter(fileName + papel + ".json")) {	    		
		    		file.write(list.toJSONString());
		    		file.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}   
    		status = "Dados TradingView OK!";
    	} finally {
			if(reader != null) {
				reader.close();
			}
		}
	    	
    	System.out.println(status);
    	     
        return status;			
		
	}
	
	public String csvFile(String papel) throws IOException, IllegalArgumentException, FeedException{
		
		String status 	= null;
		LocalDate today = LocalDate.now();		
		
		String SAMPLE_CSV_FILE 	= papel+".csv";
		
		JSONArray list 		= new JSONArray();
    	URL urlTv 			= new URL("https://br.tradingview.com/feed/?symbol=" + papel);
    	XmlReader reader 	= null;
		
		try (
			
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("title","link","description","classe"));
			
		){
			
			reader 			= new XmlReader(urlTv);
    		SyndFeed feed 	= new SyndFeedInput().build(reader);
				
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {			    			
    			SyndEntry entry = (SyndEntry) i.next();	
    			
    			String url 	= entry.getLink();
				String t1 	= url.replace("/v/","/chart/"+papel.toUpperCase()+"/");
            	String t2 	= t1.replace("http", "https");
            	//System.out.println(t2);
            	
            	Document document = Jsoup.connect(t2).followRedirects(false).timeout(6000).get();
            	if(!document.body().getElementsByClass("tv-idea-label").isEmpty()) {
            		String value = document.body().getElementsByClass("tv-idea-label").get(0).text();
            		csvPrinter.printRecord(entry.getTitle(), entry.getLink(), frmt.unescapeHTML(entry.getDescription().getValue(), 0), value);
            	} else {
            		String value = null;
            		csvPrinter.printRecord(entry.getTitle(), entry.getLink(), frmt.unescapeHTML(entry.getDescription().getValue(), 0), value);
            	}
    			    			
			}
			
			csvPrinter.flush();
			status = "Arquivo CSV gerado!";
			
		}
		
		System.out.println(status);
		
		return status;
		
	}

	public String convertJtoC() throws ParseException, JSONException {
		
		String status = null;
		
		JSONParser parser = new JSONParser();
		
		String open = "C:\\Users\\vitor\\Documents\\GetDataset\\TradingView\\2018-09-10\\jbss3.json";
		
		org.json.JSONArray out;
		
		try {
			
//			Object obj = parser.parse(new FileReader("C:\\Users\\vitor\\Documents\\GetDataset\\TradingView\\2018-09-10\\jbss3.json"));
			
//			org.json.JSONArray array = (org.json.JSONArray) obj;
			
			org.json.JSONArray docs = out.getJSONArray(open);
			
			File file = new File("C:\\Users\\vitor\\Documents\\GetDataset\\TradingView\\2018-09-10\\jbss3.csv");
			
			String csv = CDL.rowToString(array);
			FileUtils.writeStringToFile(file, csv);
			
			status = "ok"; 
			
		}  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		
		System.out.println(status);
		
		return status;
		
	}

}



























