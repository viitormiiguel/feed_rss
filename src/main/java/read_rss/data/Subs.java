package read_rss.data;

public class Subs {
	
	 private Subs() {}

	 public static String [][] htmlEscape =
     {{  "&lt;"     , "<" } ,  {  "&gt;"     , ">" } ,
	  {  "&amp;"    , "&" } ,  {  "&quot;"   , "\"" } ,
	  {  "&agrave;" , "à" } ,  {  "&Agrave;" , "À" } ,
	  {  "&acirc;"  , "â" } ,  {  "&auml;"   , "ä" } ,
	  {  "&Auml;"   , "Ä" } ,  {  "&Acirc;"  , "Â" } ,
	  {  "&aring;"  , "å" } ,  {  "&Aring;"  , "Å" } ,
	  {  "&aelig;"  , "æ" } ,  {  "&AElig;"  , "Æ" } ,
	  {  "&ccedil;" , "ç" } ,  {  "&Ccedil;" , "Ç" } ,
	  {  "&eacute;" , "é" } ,  {  "&Eacute;" , "É" } ,
	  {  "&egrave;" , "è" } ,  {  "&Egrave;" , "È" } ,
	  {  "&ecirc;"  , "ê" } ,  {  "&Ecirc;"  , "Ê" } ,
	  {  "&euml;"   , "ë" } ,  {  "&Euml;"   , "Ë" } ,
	  {  "&iuml;"   , "ï" } ,  {  "&Iuml;"   , "Ï" } ,
	  {  "&ocirc;"  , "ô" } ,  {  "&Ocirc;"  , "Ô" } ,
	  {  "&ouml;"   , "ö" } ,  {  "&Ouml;"   , "Ö" } ,
	  {  "&oslash;" , "ø" } ,  {  "&Oslash;" , "Ø" } ,
	  {  "&szlig;"  , "ß" } ,  {  "&ugrave;" , "ù" } ,
	  {  "&Ugrave;" , "Ù" } ,  {  "&ucirc;"  , "û" } ,
	  {  "&Ucirc;"  , "Û" } ,  {  "&uuml;"   , "ü" } ,
	  {  "&Uuml;"   , "Ü" } ,  {  "&nbsp;"   , " " } ,
	  {  "&copy;"   , "\u00a9" } , { "&atilde;", "ã" },
	  {  "&reg;"    , "\u00ae" } , { "&Atilde;", "Â" },
	  {  "&euro;"   , "\u20a0" } , {"&Agrave;" ,"À"},
	  {"&Aacute;" ,"Á"}, {"&Acirc;" ,"Â"}, {"&Atilde;" ,"Ã"}, {"&Auml;" ,"Ä"},
	  {"&Aring;" ,"Å"}, {"&AElig;" ,"Æ"}, {"&Ccedil;" ,"Ç"}, {"&Egrave;" ,"È"},
	  {"&Eacute;" ,"É"}, {"&Ecirc;" ,"Ê"}, {"&Euml;" ,"Ë"}, {"&Igrave;" ,"Ì"},
	  {"&Iacute;" ,"Í"}, {"&Icirc;" ,"Î"}, {"&Iuml;" ,"Ï"}, {"&ETH;" ,"Ð"},
	  {"&Ntilde;" ,"Ñ"}, {"&Ograve;" ,"Ò"}, {"&Oacute;" ,"Ó"}, {"&Ocirc;" ,"Ô"},
	  {"&Otilde;" ,"Õ"}, {"&Ouml;" ,"Ö"}, {"&Oslash;" ,"Ø"}, {"&Ugrave;" ,"Ù"},
	  {"&Uacute;" ,"Ú"}, {"&Ucirc;" ,"Û"}, {"&Uuml;" ,"Ü"}, {"&Yacute;" ,"Ý"},
	  {"&THORN;" ,"Þ"}, {"&szlig;" ,"ß"}, {"&agrave;" ,"à"}, {"&aacute;" ,"á"},
	  {"&acirc;" ,"â"}, {"&atilde;" ,"ã"}, {"&auml;" ,"ä"}, {"&aring;" ,"å"},
	  {"&aelig;" ,"æ"}, {"&ccedil;" ,"ç"}, {"&egrave;" ,"è"}, {"&eacute;" ,"é"},
	  {"&ecirc;" ,"ê"},  {"&euml;" ,"ë"}, {"&igrave;" ,"ì"}, {"&iacute;" ,"í"},
	  {"&icirc;" ,"î"}, {"&iuml;" ,"ï"}, {"&eth;" ,"ð"}, {"&ntilde;" ,"ñ"},
	  {"&ograve;" ,"ò"}, {"&oacute;" ,"ó"}, {"&ocirc;" ,"ô"}, {"&otilde;" ,"õ"},
	  {"&ouml;" ,"ö"}, {"&oslash;" ,"ø"}, {"&ugrave;" ,"ù"}, {"&uacute;" ,"ú"},
	  {"&ucirc;" ,"û"}, {"&uuml;" ,"ü"}, {"&yacute;" ,"ý"}, {"&thorn;" ,"þ"},
	  {"&yuml;" ,"ÿ"}
     };
	 
	 public static final String unescapeHTML(String s, int start){
	     int i, j, k;

	     i = s.indexOf("&", start);
	     start = i + 1;
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
	 
	  public static void main(String args[]) throws Exception {
	      // to see accented character to the console
	      java.io.PrintStream ps = new java.io.PrintStream(System.out, true, "Cp850");
	      String test = "Pesquisa eleitoral, declara&ccedil;&otilde;es do presidente dos EUA, Donald Trump, e o notici&aacute;rio corporativo est&atilde;o no radar dos investidores";
	      System.out.println(unescapeHTML(test, 0));
//	      ps.println(test + "\n-->\n" +unescapeHTML(test, 0));

	  }

}
