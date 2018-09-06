package read_rss.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;



public class TradingViewData {
	
	 public static String [][] htmlEscape =
	{{  "&lt;"     , "<" } ,  {  "&gt;"     , ">" }, {  "&amp;"    , "&" } ,  {  "&quot;"   , "\"" } ,
	  {  "&agrave;" , "à" } ,  {  "&Agrave;" , "À" }, {  "&acirc;"  , "â" } ,  {  "&auml;"   , "ä" } ,
	  {  "&Auml;"   , "Ä" } ,  {  "&Acirc;"  , "Â" } , {  "&aring;"  , "å" } ,  {  "&Aring;"  , "Å" } ,
	  {  "&aelig;"  , "æ" } ,  {  "&AElig;"  , "Æ" } , {  "&ccedil;" , "ç" } ,  {  "&Ccedil;" , "Ç" } ,
	  {  "&eacute;" , "é" } ,  {  "&Eacute;" , "É" } , {  "&egrave;" , "è" } ,  {  "&Egrave;" , "È" } ,
	  {  "&ecirc;"  , "ê" } ,  {  "&Ecirc;"  , "Ê" } , {  "&euml;"   , "ë" } ,  {  "&Euml;"   , "Ë" } ,
	  {  "&iuml;"   , "ï" } ,  {  "&Iuml;"   , "Ï" } , {  "&ocirc;"  , "ô" } ,  {  "&Ocirc;"  , "Ô" } ,
	  {  "&ouml;"   , "ö" } ,  {  "&Ouml;"   , "Ö" } , {  "&oslash;" , "ø" } ,  {  "&Oslash;" , "Ø" } ,
	  {  "&szlig;"  , "ß" } ,  {  "&ugrave;" , "ù" } , {  "&Ugrave;" , "Ù" } ,  {  "&ucirc;"  , "û" } ,
	  {  "&Ucirc;"  , "Û" } ,  {  "&uuml;"   , "ü" } , {  "&Uuml;"   , "Ü" } ,  {  "&nbsp;"   , " " } ,
	  {  "&copy;"   , "\u00a9" } , { "&atilde;", "ã" }, {  "&reg;"    , "\u00ae" } , { "&Atilde;", "Â" },
	  {  "&euro;"   , "\u20a0" } , {"&Agrave;" ,"À"}, {  "&Aacute;" , "Á" }, {  "&Acirc;" , "Â" },
	  {  "&Atilde;" , "Ã" }, {  "&Auml;"   ,"Ä" },    {  "&Aring;"  ,"Å" }, {  "&AElig;"  ,"Æ"}, 
	  {  "&Ccedil;" , "Ç" }, {  "&Egrave;" ,"È" },    {  "&Eacute;" ,"É" }, {  "&Ecirc;"  ,"Ê"}, 
	  {  "&Euml;"   , "Ë" }, {  "&Igrave;" ,"Ì" },    {  "&Iacute;" ,"Í" }, {  "&Icirc;"  ,"Î"}, 
	  {  "&Iuml;"   , "Ï" }, {  "&ETH;"    ,"Ð" },    {  "&Ntilde;" ,"Ñ" }, {  "&Ograve;" ,"Ò"},
	  {  "&Oacute;" , "Ó" }, {  "&Ocirc;"  ,"Ô" }, 	  {  "&Otilde;" ,"Õ" }, {  "&Ouml;"   ,"Ö"}, 
	  {  "&Oslash;" , "Ø" }, {  "&Ugrave;" ,"Ù" }, 	  {  "&Uacute;" ,"Ú" }, {  "&Ucirc;"  ,"Û"}, 
	  {  "&Uuml;"   , "Ü" }, {  "&Yacute;" ,"Ý" }, 	  {  "&THORN;"  ,"Þ" }, {  "&szlig;"  ,"ß"}, 
	  {  "&agrave;" , "à" }, {  "&aacute;" ,"á" }, 	  {  "&acirc;"  ,"â" }, {  "&atilde;" ,"ã"},
	  {  "&auml;"   , "ä" }, {  "&aring;"  ,"å" }, 	  {  "&aelig;"  ,"æ" }, {  "&ccedil;" ,"ç"},
	  {  "&egrave;" , "è" }, {  "&eacute;" ,"é" }, 	  {  "&ecirc;"  ,"ê" }, {  "&euml;"   ,"ë"},
	  {  "&igrave;" , "ì" }, {  "&iacute;" ,"í" }, 	  {  "&icirc;"  ,"î" }, {  "&iuml;"   ,"ï"}, 
	  {  "&eth;"    , "ð" }, {  "&ntilde;" ,"ñ" }, 	  {  "&ograve;" ,"ò" }, {  "&oacute;" ,"ó"}, 
	  {  "&ocirc;"  , "ô" }, {  "&otilde;" ,"õ" }, 	  {  "&ouml;"   ,"ö" }, {  "&oslash;" ,"ø"}, 
	  {  "&ugrave;" , "ù" }, {  "&uacute;" ,"ú" },    {  "&ucirc;"  ,"û" }, {  "&uuml;" ,  "ü"}, 
	  {  "&yacute;" , "ý" }, {  "&thorn;"  ,"þ" },    {  "&yuml;"   ,"ÿ" }
    };

	public static final String unescapeHTML(String s, int start){
	 	 int i, j, k;
	     i 		= s.indexOf("&", start);
		 start 	= i + 1;	     
		 if (i > -1) {
			 j = s.indexOf(";" ,i);
	    	 if (j > i) {
	    		 String temp = s.substring(i , j + 1);
	    		 k = 0;
	    		 while (k < htmlEscape.length) {
	    			 if (htmlEscape[k][0].equals(temp)) break;
	    			 else k++;
	    		 }	
	    		 if (k < htmlEscape.length) {
	    			 s = s.substring(0 , i)
	                 + htmlEscape[k][1] + s.substring(j + 1);
	    			 return unescapeHTML(s, i);
	    		 }
	         }
	     }
	     return s;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
				tec.put("description", unescapeHTML(entry.getDescription().getValue(), 0));	
				tec.put("link", entry.getLink());
				
				list.add(tec);								
				try (FileWriter file = new FileWriter(fileName + papel + ".json")) {	    		
		    		file.write(list.toJSONString());
		    		file.flush();
		    		status = "ok";
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}   
    		
    	} finally {
			if(reader != null) {
				reader.close();
			}
		}
	    	
    	System.out.println(status);
    	    	
    	JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("c:\\Users\\vitor\\Documents\\GetDataset\\TradingView\\"+today+"\\"+papel+".json"));

            JSONArray jsonObject = (JSONArray) obj;
            
            for (int i = 1; i < jsonObject.size() ; i++) {
            	
            	String ob = jsonObject.get(i).toString();
            	
            	JSONObject teste = (JSONObject) parser.parse(ob);
            	
            	String url = (String) teste.get("link");
//            	System.out.println(url);   	
            	
            	String t1 = url.replace("/v/","/chart/"+papel.toUpperCase()+"/");
            	String t2 = t1.replace("http", "https");
            	System.out.println(t2);
            	
            	Document document = Jsoup.connect(t2).followRedirects(false).timeout(6000).get();
        		String value = document.body().getElementsByClass("tv-idea-label").get(0).text();
        		System.out.println(value);
            	
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		        
        return status;	
		
		
	}

}



























