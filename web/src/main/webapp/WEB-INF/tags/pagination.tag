<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="pager" required="true" type="com.suwadi.web.pagination.PagedResultSet" rtexprvalue="true"%>

<c:url value="?page=${pager.firstPage}" var="firstPageUrl">
	<c:forEach items="${paramValues}" var="currentParam">
		<c:if test="${currentParam.key != 'page' && currentParam.key != 'pageSize' }">
			<c:forEach items="${currentParam.value}" var="p">
				<c:param name="${currentParam.key}" value="${p}" />
			</c:forEach>
		</c:if>
	</c:forEach>
</c:url>
<script type="text/javascript">
	$(function() {
		$('#itemsPerPage').change(function() {
			var params = '${firstPageUrl}' + "&pageSize=" + $('#itemsPerPage').val();
			window.location.href = "${contextPath}" + params;
		});
	});

</script>
<table>
	<tr>
		<td style="padding: 0px;">
			<div class="pagination">
				<ul class="pagination">
					<c:if test="${pager.pager.page != pager.firstPage}">
						<c:url value="?page=${pager.firstPage}" var="url">
							<c:forEach items="${paramValues}" var="currentParam">
								<c:if
									test="${currentParam.key != 'page' && currentParam.key != 'pageSize' }">
									<c:forEach items="${currentParam.value}" var="p">
										<c:param name="${currentParam.key}" value="${p}" />
									</c:forEach>
								</c:if>
							</c:forEach>
						</c:url>
						<li><a href="${url}">First</a></li>
					</c:if>
					<c:forEach items="${pager.pages}" var="page">
						<c:choose>
							<c:when test="${pager.pager.page == page}">
								<li class="active">${page}</li>
							</c:when>
							<c:otherwise>
								<c:url value="?page=${page}&pageSize=${pager.pager.pageSize}"
									var="url">
									<c:forEach items="${paramValues}" var="currentParam">
										<c:if
											test="${currentParam.key != 'page' && currentParam.key != 'pageSize' }">
											<c:forEach items="${currentParam.value}" var="p">
												<c:param name="${currentParam.key}" value="${p}" />
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:url>
								<li><a href="${url}">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pager.pager.page != pager.lastPage && pager.lastPage > 2}">
						<c:url value="?page=${pager.lastPage}" var="url">
							<c:forEach items="${paramValues}" var="currentParam">
								<c:if
									test="${currentParam.key != 'page' && currentParam.key != 'pageSize' }">
									<c:forEach items="${currentParam.value}" var="p">
										<c:param name="${currentParam.key}" value="${p}" />
									</c:forEach>

								</c:if>
							</c:forEach>
						</c:url>
						<li><a href="${url}">Last</a></li>
					</c:if>
					<li>
					<select name="itemsPerPage" id="itemsPerPage">
						<option <c:if test="${param.pageSize == '20'}">selected="selected"</c:if> >20</option>
						<option <c:if test="${param.pageSize == '50'}">selected="selected"</c:if> >50</option>
						<option <c:if test="${param.pageSize == '100'}">selected="selected"</c:if> >100</option>
						<option <c:if test="${param.pageSize == '500'}">selected="selected"</c:if> >500</option>
					</select>
					</li>
				</ul>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<c:choose>
				<c:when	test="${pager.rowCount <= ((pager.pager.page) * pager.pager.pageSize)}">
						Showing ${((pager.pager.page - 1) * pager.pager.pageSize) + 1} to ${pager.rowCount } of ${pager.rowCount }
				</c:when>
				<c:otherwise>
						Showing ${((pager.pager.page - 1) * pager.pager.pageSize) + 1} to ${((pager.pager.page) * pager.pager.pageSize)} of ${pager.rowCount }
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
