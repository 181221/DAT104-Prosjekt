<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../../partials/header.jsp" />
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="public/js/charts/eventResultaterChart.js"></script>

<script type="text/javascript">
	<c:set var = "a" scope = "request" value = "${aktivitet}"/>
	<c:set var = "e" scope = "request" value = "${event}"/>
	<c:set var = "melding" scope = "page" value = "Tilbakemeldinger for"/>
	<c:set var = "ft" scope = "request" value = "${requestScope.formaterteTilbakemeldinger}"/>
	
	<c:choose>
		<c:when test="${not empty ft}">
			enable = true;
			var array = [ ['Tid', 'Fornoyd', 'Noytral', 'Misfornoyd'], 
			<c:forEach items="${ft}" var="t">
				[new Date("${t.tid.toString()}"), ${t.fornoyd}, ${t.noytral}, ${t.misfornoyd}],
			</c:forEach>
			];
		</c:when>
		<c:otherwise>
			<c:set var = "melding" scope = "page" value = "Det finnes ingen tilbakemeldinger for"/>
		</c:otherwise>
	</c:choose>
</script>
<div class="ui container" style="color:teal; text-align:center;">
	<h3>Aktivitet - ${a.navn}:</h3>
	<h3>${melding} "${e.navn}"</h3>
</div>
<c:if test = "${not empty ft}">
  <div class="ui container">
    <div id="chart_div"></div>
    <div id="filter_div"></div>
    <button class="ui blue basic button" id="byttKnapp" onclick="switchView()"><i class="bar chart icon"></i>Bytt graf</button>
    <button class="ui green basic button" id="green" onclick="showColumn(1, this.id)"><i class="smile icon"></i>Vis forn�yd</button>
    <button class="ui orange basic button" id="orange" onclick="showColumn(2, this.id)"><i class="meh icon"></i>Vis n�ytral</button>
    <button class="ui red basic button" id="red" onclick="showColumn(3, this.id)"><i class="frown icon"></i>Vis misforn�yd</button>
    <button class="ui blue basic button" id="visAlleKnapp" onclick="showAll()"><i class="ellipsis horizontal icon"></i>Vis alle</button>
  </div>
 </c:if>
<jsp:include page="../../../partials/footer.jsp" />