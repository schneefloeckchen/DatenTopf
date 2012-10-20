<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="catalogs.title" /></title>
  </head>
  <body>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton/></li>
        <li><topf:logoutButton/></li>
      </ul>
    </div>
    <div class="nav">
      <g:link action="index" controller="country">Country</g:link>
      <g:link action="index" controller="salut">Salutation</g:link>
      <g:link action="index" controller="telProvider">Telco Provider</g:link>
      <g:link action="index" controller="telType">Telephone Line Type</g:link>
      <g:link action="index" controller="title">Title</g:link>
      <g:link action="index" controller="user">User</g:link>
      <g:link action="index" controller="person">Person</g:link>
    </div>
  </body>
</html>
