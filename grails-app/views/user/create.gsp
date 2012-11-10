<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="user.create.title" /></title>
  </head>
  <body>
    <h1><g:message code="user.create.title" /></h1>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton /></li>
        <li><topf:listButton dbClass="User" /></li>
      </ul>
    </div>
    <topf:dialogError instance="${userInstance}" />
  <g:form action="save" >
    <div id="create-user" class="content scaffold-create" role="main">
      <topf:dialogField labelCode="user.name.label" field="name" instance="${userInstance}"/>
      <topf:dialogCombo labelCode="user.person.label" field="person" instance="${userInstance}" fromClass="topf.addy.Person" />
      <topf:dialogField labelCode="user.password.label" field="pwd" />
      <topf:dialogCheckBox labelCode="user.superUser.label" field="superUser" instance="${userInstance}" />
      <topf:dialogCheckBox labelCode="user.disabled.label" field="disabled" instance="${userInstance}" />
    </div>
    <topf:dialogSaveButton />
  </g:form>
</body>
</html>
