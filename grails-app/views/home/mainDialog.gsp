<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="mainDialog.title" /></title>
  </head>
  <body>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton/></li>
        <li><topf:logoutButton/></li>
        <li><g:link class="list" action="catalogs"><g:message code="mainDialog.catalogs" /></g:link></li>
        <li><g:link class="list" action="list" controller="address"><g:message code="mainDialog.address.list" /></g:link></li>
        <li><g:link class="list" controller="address" action="create"><g:message code="mainDialog.address.add" /></g:link></li>
      </ul>
    </div>
    <div class="nav">
      <g:link action="index" controller="person">Person</g:link>
      <g:link action="index" controller="address">Address</g:link>
    </div>
  </body>
</html>
