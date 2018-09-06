package read_rss.data;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TesteRead {
	
	public static void main(String[] args) throws IOException {
		
		String t1 = "http://br.tradingview.com/v/qSGMb7ra/";
	      
		Document  document 	= Jsoup.connect(t1).followRedirects(false).timeout(5000).get();
		String value 		= document.body().getElementsByClass("tv-idea-label").get(0).text();
		
		System.out.println(value);
		
	} 
	
}
