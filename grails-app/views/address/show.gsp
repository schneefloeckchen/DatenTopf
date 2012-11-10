<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="address.show.title" /></title>
  </head>
  <body>
    <div class="nav" role="navigation">
      <ul>
        <li><topf:homeButton/></li>
        <li><topf:logoutButton/></li>
        <li><topf:listButton dbClass="Address" /></li>
      </ul>
    </div>
  <topf:dialogError instance="${addressInstance}" />
  <div id="show-pers" class="content scaffold-show" role="main">
    <h1><g:message code="address.show.title" /></h1>
    <table>
      <topf:viewRow instance="${addressInstance}" fields="['name', 'country']" />
      <topf:viewRow instance="${addressInstance}" fields="['fullTown', 'fullStreet']" />
    </table>
    <h1><g:message code="address.show.persons" /></h1>
    <table>
    <topf:tableHeader domain="person" fields="['salutation', 'title', 'fullName', 'telefon']" />
    <tbody>
      <g:each in="${addressInstance.persons}" var="person">
        <tr>
          <td></td>
          <td><g:fieldValue bean="${person}" field="salutation" /></td>
          <td><g:fieldValue bean="${person}" field="title" /></td>
          <td><g:fieldValue bean="${person}" field="fullName" /></td>
          <td>
          <g:each in="${person.telNumbers}" var="telNumber">
            <g:fieldValue bean="${telNumber}" field="telefonInfo" />
          </g:each>
          </td>
        </tr>
      </g:each>
    </tbody>
    </table>
    <div class="buttons">
    <topf:dialogAddButton instance="${addressInstance}" childDomain="person" />
    </div>
  </div>
</body>
</html>
