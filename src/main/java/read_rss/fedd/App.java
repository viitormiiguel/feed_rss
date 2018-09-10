package read_rss.fedd;

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
    	    	    	
    	sampleM.InfoMoney("acoes", "https://www.infomoney.com.br/onde-investir/acoes/rss");
    	sampleM.InfoMoney("analise-tecnica", "https://www.infomoney.com.br/mercados/analise-tecnica/rss");
    	sampleM.InfoMoney("bitcoin", "https://www.infomoney.com.br/mercados/bitcoin/rss");
    	sampleM.InfoMoney("acoes-indices", "https://www.infomoney.com.br/mercados/acoes-e-indices/rss");
    	
    	sampleC.Investing("analise-tecnica-mercado", "https://br.investing.com/rss/market_overview_Technical.rss");
    	sampleC.Investing("analise-funda-mercado", "https://br.investing.com/rss/market_overview_Fundamental.rss");
    	sampleC.Investing("analise-tecnica-acoes", "https://br.investing.com/rss/stock_Technical.rss");
    	sampleC.Investing("analise-funda-acoes", "https://br.investing.com/rss/stock_Fundamental.rss");
    	sampleC.Investing("opiniao-mercado", "https://br.investing.com/rss/stock_Opinion.rss"); 

    }
    
}
