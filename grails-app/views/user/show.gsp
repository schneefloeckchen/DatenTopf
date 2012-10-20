<%@ page contentType="text/html;charset=UTF-8"%>

<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="user.show.title" /></title>
  </head>
  <body>
    <h1>
      <g:message code="user.show.title" />
    </h1>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton /></li>
        <li><topf:listButton dbClass="User" /></li>
      </ul>
    </div>
  <topf:dialogError instance="${userInstance}" />
  <ol class="property-list user">
    <topf:displayText labelCode="user.name.label" field="name" instance="${userInstance}" />
    <topf:displayText labelCode="user.person.label" field="person" instance="${userInstance}" />
    <topf:displayDateTime labelCode="user.created.label" field="created" instance="${userInstance}" />
    <topf:displayDateTime labelCode="user.updated.label" field="updated" instance="${userInstance}" />
    <topf:displayDateTime labelCode="user.lastLogin.label" field="lastLogin" instance="${userInstance}" />
    <topf:displayBoolean labelCode="user.superUser.label" field="superUser" instance="${userInstance}" />
    <topf:displayBoolean labelCode="user.disabled.label" field="disabled" instance="${userInstance}" />
  </ol>
  <topf:dialogEditButton id="${userInstance?.id}" delete="no" />
</body>
</html>
