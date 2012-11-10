<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="person.add.label" /></title>
  </head>
  <body>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton/></li>
        <li><topf:logoutButton/></li>
      </ul>
    </div>
  <topf:dialogError instance="${personInstance}" />

  <h1><g:message code="person.add.label" /></h1>
  id is -- ${id} -- version is ${version}<br>
  <g:form action="addSavePerson">
    <div id="create-address" class="content scaffold-create" role="main">
      <topf:dialogCombo labelCode="person.salutation.label" field="salutation" instance="${personInstance}" fromClass="topf.catalog.Salut" />
      <topf:dialogCombo labelCode="person.title.label" field="title" instance="${personInstance}" fromClass="topf.catalog.Title" />
      <topf:dialogField labelCode="person.firstName.label" field="firstName" instance="${personInstance}"/>
      <topf:dialogField labelCode="person.lastName.label" field="lastName" instance="${personInstance}"/>
      <topf:dialogField labelCode="person.email.label" field="email" instance="${personInstance}"/>
      <topf:dialogDateChooser labelCode="person.birthday.label" field="birthday" instance="${personInstance}"/>
    </div>
    <topf:dialogAddSaveButton childDomain="person" id="${id}" version="${version}"/>
  </g:form>
</body>
</html>
