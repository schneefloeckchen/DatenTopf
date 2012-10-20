<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="loginPage.title" /></title>
  </head>
  <body>
  <h1><g:message code="loginPage.title" /></h1>
  <topf:dialogError />
  <g:form action="login">
    <fieldset class="form">
      <div class="fieldcontain">
        <label for="user"><g:message code="loginPage.user" /></label>
        <g:textField name="user"/>
      </div>
      <div class="fieldcontain">
        <label for="password"><g:message code="loginPage.password" /></label>
        <g:passwordField name="password"/>
      </div>
    </fieldset>
    <fieldset class="buttons">
      <g:submitButton name="create" class="save" value="${message(code: 'loginPage.button')}" />
      <g:actionSubmit value="Force Login" action="forceLogin" />
    </fieldset>
  </g:form>
</body>
</html>
