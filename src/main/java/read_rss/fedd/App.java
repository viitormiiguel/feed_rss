package 
read_rss.fedd;

import read_rss.data.InfoMoneyData;
import read_rss.data.InvestingData;
import read_rss.data.TradingViewData;

public class App {
	
    public static void main( String[] args ) throws Exception {
    	
		final TradingViewData sampleT 	= new TradingViewData();
		final InfoMoneyData sampleM 	= new InfoMoneyData();
		final InvestingData sampleC		= new InvestingData();
		
		// ACÇÕES INDIVIDUAIS
    	sampleT.TradingView("jbss3");
    	sampleT.TradingView("petr4");
    	sampleT.TradingView("aapl");
    	sampleT.TradingView("itub4");
    	sampleT.TradingView("vale3");
    	sampleT.TradingView("btcusd");
    	sampleT.TradingView("natu3");
    	sampleT.TradingView("ltcbrl");
    	sampleT.TradingView("ibov");
    	sampleT.TradingView("itsa4");
    	sampleT.TradingView("goll4");
    	sampleT.TradingView("bbas3");
    	sampleT.TradingView("abev3");
    	sampleT.TradingView("usim5");
    	sampleT.TradingView("ggbr4");
    	sampleT.TradingView("rent3");
    	sampleT.TradingView("lame4");
    	sampleT.TradingView("mglu3");
    	sampleT.TradingView("brfs3");
    	sampleT.TradingView("ciel3");
		sampleT.TradingView("bbdc4");
		sampleT.TradingView("csna3");
    	
    	sampleT.csvWriter("jbss3");
    	sampleT.csvWriter("petr4");
    	sampleT.csvWriter("aapl");
    	sampleT.csvWriter("itub4");
    	sampleT.csvWriter("vale3");
    	sampleT.csvWriter("btcusd");
    	sampleT.csvWriter("natu3");
    	sampleT.csvWriter("ltcbrl");
    	sampleT.csvWriter("ibov");
    	sampleT.csvWriter("itsa4");
    	sampleT.csvWriter("goll4");
    	sampleT.csvWriter("bbas3");
    	sampleT.csvWriter("abev3");
    	sampleT.csvWriter("usim5");
    	sampleT.csvWriter("ggbr4");
    	sampleT.csvWriter("rent3");
    	sampleT.csvWriter("lame4");
    	sampleT.csvWriter("mglu3");
    	sampleT.csvWriter("brfs3");
    	sampleT.csvWriter("ciel3");
    	sampleT.csvWriter("bbdc4");
    	sampleT.csvWriter("csna3");
    	
    	sampleT.mergeFile();

    	sampleM.InfoMoney("acoes", "https://www.infomoney.com.br/onde-investir/acoes/rss");
    	sampleM.InfoMoney("analise-tecnica", "https://www.infomoney.com.br/mercados/analise-tecnica/rss");
    	sampleM.InfoMoney("bitcoin", "https://www.infomoney.com.br/mercados/bitcoin/rss");
    	sampleM.InfoMoney("acoes-indices", "https://www.infomoney.com.br/mercados/acoes-e-indices/rss"); 	    	
    	sampleM.InfoMoney("blog-lux", "https://www.infomoney.com.br/blogs/blog-da-lux/rss");
    	sampleM.InfoMoney("blog-alvaro", "https://www.infomoney.com.br/blogs/blog-do-alvaro-bandeira/rss");
    	sampleM.InfoMoney("blog-daniel", "https://www.infomoney.com.br/blogs/blog-do-daniel-resende/rss");
    	sampleM.InfoMoney("blog-juliano", "https://www.infomoney.com.br/blogs/blog-do-juliano-carneiro/rss");
    	sampleM.InfoMoney("precos", "https://www.infomoney.com.br/minhas-financas/precos/rss");
    	
    	sampleM.csvWriter("acoes");
    	sampleM.csvWriter("analise-tecnica");
    	sampleM.csvWriter("bitcoin");
    	sampleM.csvWriter("acoes-indices");
    	sampleM.csvWriter("blog-lux");
    	sampleM.csvWriter("blog-alvaro");
    	sampleM.csvWriter("blog-daniel");
    	sampleM.csvWriter("blog-juliano");
    	sampleM.csvWriter("precos");
    	
    	sampleM.mergeFile();
    	
    	sampleC.Investing("analise-tecnica-mercado", "https://br.investing.com/rss/market_overview_Technical.rss");
    	sampleC.Investing("analise-funda-mercado", "https://br.investing.com/rss/market_overview_Fundamental.rss");
    	sampleC.Investing("analise-tecnica-acoes", "https://br.investing.com/rss/stock_Technical.rss");
    	sampleC.Investing("analise-funda-acoes", "https://br.investing.com/rss/stock_Fundamental.rss");
    	sampleC.Investing("opiniao-mercado", "https://br.investing.com/rss/stock_Opinion.rss"); 
    	sampleC.Investing("stock-fundamental", "https://br.investing.com/rss/stock_Fundamental.rss");
    	sampleC.Investing("mais-lidas", "https://br.investing.com/rss/news_285.rss");
    	sampleC.Investing("politicas", "https://br.investing.com/rss/news_289.rss");
    	sampleC.Investing("noticias-mercado", "https://br.investing.com/rss/news_25.rss");
    	sampleC.Investing("noticias-economia", "https://br.investing.com/rss/news_14.rss");
		
		sampleC.csvWriter("analise-tecnica-mercado");
		sampleC.csvWriter("analise-funda-mercado");
		sampleC.csvWriter("analise-tecnica-acoes");
		sampleC.csvWriter("analise-funda-acoes");
		sampleC.csvWriter("opiniao-mercado");
		sampleC.csvWriter("stock-fundamental");
		sampleC.csvWriter("mais-lidas");
		sampleC.csvWriter("politicas");
		sampleC.csvWriter("noticias-mercado");
		sampleC.csvWriter("noticias-economia");
		
		sampleC.mergeFile();

    }
    
}
