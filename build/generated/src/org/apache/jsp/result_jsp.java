package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import twitter4j.*;
import twitter4j.conf.*;
import StringMatcher.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public final class result_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 
ConfigurationBuilder cb = new ConfigurationBuilder();
cb.setDebugEnabled(true)
  .setOAuthConsumerKey("O2sjEHgYJcq6sR6My7im7WdKM")
  .setOAuthConsumerSecret("KZXSFSBYWWYQ6jgCohXXZyRdmt1U6PZFfMVWgY4V9ql3Abnc0e")
  .setOAuthAccessToken("61489334-zKjvnwn54H8wOqSA3GerwznQsl7oILOGRl3rLedHY")
  .setOAuthAccessTokenSecret("Wun65KMASD6UVmceeB0oVGUewzvLh8WsCdQCgHHnSo6p3")
  .setHttpProxyHost("cache.itb.ac.id")
  .setHttpProxyPort(8080)
  .setHttpProxyUser("hanscg")
  .setHttpProxyPassword("enterprise1701");

TwitterFactory tf = new TwitterFactory(cb.build());
Twitter twitter = tf.getInstance();

// Get these variables from the request page (use request.getParameter().split)
// List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
List<String> queryStrings = new ArrayList<String>(Arrays.asList("#olahraga","#sport"));
List<String> keywordsBola = new ArrayList<String>(Arrays.asList("epl","ynwa","fifa","isl"));
List<String> keywordsBultang = new ArrayList<String>(Arrays.asList("super series","bwf","all england","lilyana natsir"));
List<String> keywordsBasket = new ArrayList<String>(Arrays.asList("nba","lbj","euroleague"));

List<Status> statuses = new ArrayList<Status>();
List<Status> qstatuses = new ArrayList<Status>();
List<Status> statusBola = new ArrayList<Status>();
List<Status> statusBultang = new ArrayList<Status>();
List<Status> statusBasket = new ArrayList<Status>();
List<Status> statusElse = new ArrayList<Status>();

Query query = new Query();
query.setCount(100/queryStrings.size());

for ( String qString : queryStrings ) {    
    query.setQuery(qString);
    QueryResult qresult = twitter.search(query);
    qstatuses = qresult.getTweets();
    statuses.addAll(qstatuses);
}

int algo = 1;

for (Status status : statuses) {
    int keywordBolaLoc = 150;
    int keywordBultangLoc = 150;
    int keywordBasketLoc = 150;
            
    for (String pattern : keywordsBola) {
        int patternLoc = StringMatcher.MatchString(status.getText(),pattern,algo);
        if ((patternLoc != -1) && (patternLoc < keywordBolaLoc)) {
            keywordBolaLoc = patternLoc;
        }
    }
    
    for (String pattern : keywordsBultang) {
        int patternLoc = StringMatcher.MatchString(status.getText(),pattern,algo);
        if ((patternLoc != -1) && (patternLoc < keywordBolaLoc)) {
            keywordBultangLoc = patternLoc;
        }
    }
    
    for (String pattern : keywordsBasket) {
        int patternLoc = StringMatcher.MatchString(status.getText(),pattern,algo);
        if ((patternLoc != -1) && (patternLoc < keywordBolaLoc)) {
            keywordBasketLoc = patternLoc;
        }
    }
    
    if ((keywordBolaLoc < keywordBultangLoc) && (keywordBolaLoc < keywordBasketLoc)) {
        statusBola.add(status);
    } else if ((keywordBultangLoc < keywordBasketLoc) && (keywordBultangLoc < keywordBasketLoc)) {
        statusBultang.add(status);
    } else if ((keywordBasketLoc < keywordBultangLoc) && (keywordBasketLoc < keywordBolaLoc)) {
        statusBasket.add(status);
    } else {
        statusElse.add(status);
    }
}


      out.write("        \n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Result</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("            ");
 for (Status status : statuses) { 
      out.write(" \n");
      out.write("                    ");
 String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); 
      out.write("\n");
      out.write("                    ");
 String userurl= "https://twitter.com/" + status.getUser().getScreenName(); 
      out.write("\n");
      out.write("                    <h4 class=\"media-heading\"><a href=\"");
      out.print( userurl );
      out.write('"');
      out.write('>');
      out.print( status.getUser().getName() );
      out.write("</a></h4>\n");
      out.write("                    <p><a href=\"");
      out.print( url );
      out.write('"');
      out.write('>');
      out.print( status.getText() );
      out.write("</a></p>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
