<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Film location in San Francisco</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/jquery-ui-1.12.1.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script type="application/javascript" charset="UTF-8" src="/js/jquery-3.2.1.min.js"></script>
    <script type="application/javascript" charset="UTF-8" src="/js/jquery-ui-1.12.1.min.js"></script>
    <script type="application/javascript" charset="UTF-8" src="/js/bootstrap.min.js"></script>
    <script type="application/javascript" charset="UTF-8" src="/js/search.js"></script>
    <script type="application/javascript" charset="UTF-8" src="/js/maps.js"></script>
</head>
<body>
<div class="container mr-auto">
    <div class="navbar fixed-top text-center">
        <span class="col-lg-3"></span>
        <form id="searchBar" class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/data/getLocationByTitle">
            <input id="searchBox" class="form-control mr-sm-2" placeholder="Search film title..." />
            <button id="search" class="btn btn-primary my-2 my-lg-0">search</button>
        </form>
    </div>
</div>
<div id="map"></div>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDbsULxTMM5PuVNcg0ltZr5NrZPi6nZLME&callback=initMap"
        async defer></script>
</body>
</html>