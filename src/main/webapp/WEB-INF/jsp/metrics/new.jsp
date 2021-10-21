<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="newMetric">


<h2>New Measure</h2>

    <table id="archivesTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 200px;">Details</th>
           
        </tr>
        </thead>
        <tbody>
           <tr>
                <td>
                    <spring:url value="newCountMeasure?archiveId={archiveId}" var="newCountMeasureUrl">
                        <spring:param name="archiveId" value="${archiveId}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(newCountMeasureUrl)}">CountMeasure</a>
                </td>
         </tr>
          <tr>
               <td>
                    <spring:url value="newTimeMeasure?archiveId={archiveId}" var="newTimeMeasureUrl">
                        <spring:param name="archiveId" value="${archiveId}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(newTimeMeasureUrl)}">TimeMeasure</a>
                </td>
         </tr>
         <tr>
               <td>
                    <spring:url value="newDataMeasure?archiveId={archiveId}" var="newDataMeasureUrl">
                        <spring:param name="archiveId" value="${archiveId}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(newDataMeasureUrl)}">DataMeasure</a>
                </td>
         </tr>
        </tbody>
    </table>

















</petclinic:layout>