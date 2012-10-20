<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="user.list.title" /></title>
  </head>
  <body>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton /></li>
        <li><topf:createButton /></li>
      </ul>
    </div>
  <topf:dialogError instance="${userInstance}" />
  <h1><g:message code="user.list.title" /></h1>
  <table>
    <topf:tableHeader domain="user" fields="['name', 'person', 'updated', 'lastLogin', 'superUser', 'disabled']" />
    <tbody>
    <g:each in="${userInstanceList}" status="i" var="userInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <topf:tableFields instance="${userInstance}" fields="['name', 'person']" select='0'/>
        <topf:tableDatum instance="${userInstance}" fields="['updated', 'lastLogin']" />
        <topf:tableFields instance="${userInstance}" fields="['superUser', 'disabled']" />
      </tr>
    </g:each>
  </tbody>
</table>
</body>
</html>
