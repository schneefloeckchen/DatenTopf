<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="address.create.title" /></title>
  </head>
  <body>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton/></li>
        <li><topf:logoutButton/></li>
        <li><topf:listButton dbClass="Address"/></li>
      </ul>
    </div>
    <topf:dialogError instance="${personInstance}" />
    <h1><g:message code="address.create.title" /></h1>
  <g:form action="save">
    <div id="create-address" class="content scaffold-create" role="main">
      <topf:dialogField labelCode="address.name.label" field="name" instance="${addressInstance}"/>
      <topf:dialogCombo labelCode="address.country.label" field="country" instance="${addressInstance}" fromClass="topf.catalog.Country" />
      <topf:dialogField labelCode="address.street.label" field="street" instance="${addressInstance}"/>
      <topf:dialogField labelCode="address.streetNumber.label" field="streetNumber" instance="${addressInstance}"/>
      <topf:dialogField labelCode="address.addInfo.label" field="addInfo" instance="${addressInstance}"/>
      <topf:dialogField labelCode="address.town.label" field="town" instance="${addressInstance}"/>
      <topf:dialogField labelCode="address.zip.label" field="zip" instance="${addressInstance}"/>
    </div>
    <topf:dialogSaveButton />
  </g:form>
</body>
</html>
