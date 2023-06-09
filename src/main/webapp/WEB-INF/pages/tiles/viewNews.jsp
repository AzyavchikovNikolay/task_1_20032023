<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localizationVars.jsp" %>

<c:if test="${sessionScope.edit_result eq 'success'}">
			<div class=registration-success><c:out value="${edit_success}" /></div>
			<c:set var="edit_result" scope="session" value=""/>
</c:if>

<div class="body-title">
	<a href="controller?command=go_to_news_list"><c:out value="${news}" /></a> <c:out value="${view_news}" />
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text"><c:out value="${news_title}" /></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${sessionScope.newsView.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_date}" /></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${sessionScope.newsView.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_brief}" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${sessionScope.newsView.briefNews }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_content}" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${sessionScope.newsView.content }" />
				</div></td>
		</tr>
	</table>
</div>

<c:if test="${sessionScope.role eq 'admin'}">
<div class="first-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_edit_news" /> <input
			type="hidden" name="id" value="${sessionScope.newsView.idNews}" /> <input
			type="submit" value="${edit}" />
			<c:set var="for_submit_back" scope="session" value="view_news"/>
	</form>
</div>

<div class="second-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_delete_news" /> <input
			type="hidden" name="id" value="${sessionScope.newsView.idNews}" /> <input
			type="submit" value="${delete}" />
	</form>
</div>
</c:if>

<div class="first-view-button">
	<form action="controller" method="get">
		<input type="hidden" name="command" value="go_to_news_list"/>
			<div class="registration-field">
				<input type="submit" value="${back_to_news_list}"/>
			</div>
	</form>
</div>