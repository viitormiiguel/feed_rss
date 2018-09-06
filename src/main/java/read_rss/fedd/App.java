package read_rss.fedd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import read_rss.data.InfoMoneyData;
import read_rss.data.InvestingData;
import read_rss.data.TradingViewData;
import read_rss.data.TesteRead;

public class App {
	
    public static void main( String[] args ) throws Exception {
    	
//    	@SuppressWarnings("unused")
		final TradingViewData sampleT 	= new TradingViewData();
    	//@SuppressWarnings("unused")
//		final InfoMoneyData sampleM 	= new InfoMoneyData();
//    	@SuppressWarnings("unused")
//		final InvestingData sampleC		= new InvestingData();
    	
//    	sampleT.TradingView("jbss3");
    	sampleT.TradingView("petr4");
//    	sampleT.TradingView("aapl");
//    	sampleT.TradingView("itub4");
//    	    	
//    	sampleM.InfoMoney("acoes", "https://www.infomoney.com.br/onde-investir/acoes/rss");
//    	sampleM.InfoMoney("analise-tecnica", "https://www.infomoney.com.br/mercados/analise-tecnica/rss");
//    	sampleM.InfoMoney("bitcoin", "https://www.infomoney.com.br/mercados/bitcoin/rss");
//    	sampleM.InfoMoney("acoes-indices", "https://www.infomoney.com.br/mercados/acoes-e-indices/rss");
//    	
//    	sampleC.Investing("analise-tecnica-mercado", "https://br.investing.com/rss/market_overview_Technical.rss");
//    	sampleC.Investing("analise-funda-mercado", "https://br.investing.com/rss/market_overview_Fundamental.rss");
//    	sampleC.Investing("analise-tecnica-acoes", "https://br.investing.com/rss/stock_Technical.rss");
//    	sampleC.Investing("analise-funda-acoes", "https://br.investing.com/rss/stock_Fundamental.rss");
//    	sampleC.Investing("opiniao-mercado", "https://br.investing.com/rss/stock_Opinion.rss"); 
    	
//    	String url1 = "https://br.tradingview.com/v/qSGMb7ra/";
//    	String tes = url1.replace("/v/", "/chart/PETR4/");
//    	System.out.println(tes);
//    	    	
//    	Document document = Jsoup.connect(tes).followRedirects(false).timeout(6000).get();
//		String value = document.body().getElementsByClass("tv-idea-label").get(0).text();
//		System.out.println(value);
    	

    }
    
}
