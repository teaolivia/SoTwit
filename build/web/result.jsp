<%-- 
    Document   : result
    Created on : Apr 15, 2015, 4:13:46 PM
    Author     : user
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="twitter4j.*"%>
<%@page import="twitter4j.conf.*"%>
<%@page import="StringMatcher.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<% 
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

List<String> queryStrings = new ArrayList<String>(Arrays.asList(request.getParameter("query").split("[;,]\\s*")));
List<String> keywordsBola = new ArrayList<String>(Arrays.asList(request.getParameter("bola").split("[;,]\\s*")));
List<String> keywordsBultang = new ArrayList<String>(Arrays.asList(request.getParameter("bultang").split("[;,]\\s*")));
List<String> keywordsBasket = new ArrayList<String>(Arrays.asList(request.getParameter("basket").split("[;,]\\s*")));

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

int algo = Integer.parseInt(request.getParameter("algoritma"));

for (Status status : statuses) {
    int keywordBolaLoc = 150;
    int keywordBultangLoc = 150;
    int keywordBasketLoc = 150;
            
    String text = status.getText();
    
    for (String pattern : keywordsBola) {
        int patternLoc = StringMatcher.MatchString(text,pattern,algo);
        if ((patternLoc != -1) && (patternLoc < keywordBolaLoc)) {
            keywordBolaLoc = patternLoc;
        }
    }
    
    for (String pattern : keywordsBultang) {
        int patternLoc = StringMatcher.MatchString(text,pattern,algo);
        if ((patternLoc != -1) && (patternLoc < keywordBolaLoc)) {
            keywordBultangLoc = patternLoc;
        }
    }
    
    for (String pattern : keywordsBasket) {
        int patternLoc = StringMatcher.MatchString(text,pattern,algo);
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

%>        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h2> Keyword 1: </h2>
            <% for (Status status : statusBola) { %> 
                    <% String text = status.getText(); %>
                    <% for (String pattern : keywordsBola) {
                            int patternLoc = StringMatcher.MatchString(text,pattern,algo);
                            if (patternLoc >= 0) {
                                String replacement = "<b>"+text.substring(patternLoc,patternLoc+pattern.length())+"</b>";
                                text = text.substring(0,patternLoc)+replacement+text.substring(patternLoc+pattern.length());
                            }
                        }
                    %>
                    <% String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); %>
                    <% String userurl= "https://twitter.com/" + status.getUser().getScreenName(); %>
                    <h4 class="media-heading"><a href="<%= userurl %>"><%= status.getUser().getName() %></a></h4>
                    <p><a href="<%= url %>"><%= text %></a></p>
            <% } %>
        <h2> Keyword 2: </h2>
            <% for (Status status : statusBultang) { %> 
                    <% String text = status.getText(); %>
                    <% for (String pattern : keywordsBultang) {
                            int patternLoc = StringMatcher.MatchString(text,pattern,algo);
                            if (patternLoc >= 0) {
                                String replacement = "<b>"+text.substring(patternLoc,patternLoc+pattern.length())+"</b>";
                                text = text.substring(0,patternLoc)+replacement+text.substring(patternLoc+pattern.length());
                            }
                        }
                    %>
                    <% String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); %>
                    <% String userurl= "https://twitter.com/" + status.getUser().getScreenName(); %>
                    <h4 class="media-heading"><a href="<%= userurl %>"><%= status.getUser().getName() %></a></h4>
                    <p><a href="<%= url %>"><%= text %></a></p>
            <% } %>
        <h2> Keyword 3: </h2>
            <% for (Status status : statusBasket) { %> 
                    <% String text = status.getText(); %>
                    <% for (String pattern : keywordsBasket) {
                            int patternLoc = StringMatcher.MatchString(text,pattern,algo);
                            if (patternLoc >= 0) {
                                String replacement = "<b>"+text.substring(patternLoc,patternLoc+pattern.length())+"</b>";
                                text = text.substring(0,patternLoc)+replacement+text.substring(patternLoc+pattern.length());
                            }
                        }
                    %>
                    <% String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); %>
                    <% String userurl= "https://twitter.com/" + status.getUser().getScreenName(); %>
                    <h4 class="media-heading"><a href="<%= userurl %>"><%= status.getUser().getName() %></a></h4>
                    <p><a href="<%= url %>"><%= text %></a></p>
            <% } %>
        <h2> Other: </h2>
            <% for (Status status : statusElse) { %> 
                    <% String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); %>
                    <% String userurl= "https://twitter.com/" + status.getUser().getScreenName(); %>
                    <h4 class="media-heading"><a href="<%= userurl %>"><%= status.getUser().getName() %></a></h4>
                    <p><a href="<%= url %>"><%= status.getText() %></a></p>
            <% } %>
    </body>
</html>
