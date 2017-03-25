<%-- <%@ page contentType="text/html;charset=UTF-8" %> --%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="grid">
    <tiles:putAttribute name="body">
 
  <body ng-app="app">
<div ng-controller="MainCtrl">
  <div ui-grid="gridOptions" ui-grid-validate ui-grid-edit ui-grid-row-edit  ui-grid-cellnav ui-grid-pagination ui-grid-selection ui-grid-exporter ui-grid-resize-columns class="grid"></div>
</div>


<script src="<c:url value='/resources/js/controller/grid_controller.js' />"></script>
  </body>
  <c:import url="/resources/parcial/edit_confirm.html"/>
              
    </tiles:putAttribute>
</tiles:insertDefinition>