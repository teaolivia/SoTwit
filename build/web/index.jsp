<%-- 
    Document   : index
    Created on : Apr 15, 2015, 4:05:48 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SoTwit</title>
    </head>
    <body>
        <h1>SoTwit</h1>
        <form action="result.jsp" method="post" autocomplete="on">
            <label for="query">Pencarian</label>
            <input name="query" type="text" placeholder="#sport, #olahraga" value="#sport, #olahraga">
            <br>
            
            <label for="bola">Keyword 1</label>
            <input name="bola" type="text" placeholder="pssi, fifa, isl, epl, la liga" value="pssi, fifa, isl, epl, la liga">
            <br>
            
            <label for="bultang">Keyword 2</label>
            <input name="bultang" type="text" placeholder="badminton, bwf, super series, all england, indonesia open" value="bwf, super series, all england, indonesia open">
            <br>
            
            <label for="basket">Keyword 3</label>
            <input name="basket" type="text" placeholder="nba, lebron, euroleague" value="nba, lebron, euroleague">
            <br>
            <br>
            
            <label for="algoritma">Algoritma pencarian </label><br>
            <label><input type="radio" name="algoritma" value="1" checked> Knuth-Morris-Pratt </label><br>
            <label><input type="radio" name="algoritma" value="2"> Boyer-Moore</label><br>
            <br>
            
            <button type="submit">Analisa</button>
        </form>
    </body>
</html>