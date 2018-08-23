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

public class InvestingData {
	
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
	public String Investing (String tipo, String link) throws IOException, IllegalArgumentException, FeedException {
		
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
				tec1.put("title", unescapeHTML(entry.getTitle(), 0));
				tec1.put("author", entry.getAuthor());				
				list_im.add(tec1);								
				try (FileWriter file = new FileWriter("c:\\Users\\vitor\\Documents\\GetDataset\\Investing.com\\" + tipo + ".json")) {	    		
		    		file.write(list_im.toJSONString());
		    		file.flush();
		    		status = "ok - investing";
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
