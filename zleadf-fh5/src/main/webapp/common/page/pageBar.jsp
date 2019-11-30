<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>
<div class="fr listpage">
    <span>共${pagination.totalPages}页 - 共${pagination.totalCount}条数据</span>
    <pg:prev export="pageUrl, pageNumber" ifnull="<%= true %>">
        <% if (pageUrl != null) { %>
        <a href="<%= pageUrl %>&pageNumber=<%=pageNumber%>">&lt; Prev</a>
        <% } else { %>
        <span class="disabled">&lt; Prev</span>
        <% } %>
    </pg:prev>
    <pg:pages export="pageUrl, pageNumber">
        <% if (pageNumber == currentPageNumber) {%>
        <span class="current"><%= pageNumber %></span>
        <% } else { %>
        <a href="<%= pageUrl %>&pageNumber=<%=pageNumber%>"><%= pageNumber %></a>      
        <% } %>
    </pg:pages>
    <pg:next export="pageUrl, pageNumber" ifnull="<%= true %>">
        <% if (pageUrl != null) { %>
        <a href="<%= pageUrl %>&pageNumber=<%=pageNumber%>">Next &gt;</a>
        <% } else { %>
        <span class="disabled">Next &gt;</span>
        <% } %>
    </pg:next>
</div>