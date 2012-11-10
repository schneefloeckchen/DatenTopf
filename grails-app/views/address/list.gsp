<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="address.list.title" /></title>
  </head>
  <body>
  <topf:naviHeader buttons="['home', 'create', 'list']" dbClass="Address" />
  <h1><g:message code="address.list.title" /></h1>
  <table>
    <topf:tableHeader domain="address" fields="['name', 'town', 'street']" />
    <topf:tableBody instanceList="${addressInstanceList}" fields="['name', 'fullTown', 'fullStreet']" />
  </table>
</body>
</html>
