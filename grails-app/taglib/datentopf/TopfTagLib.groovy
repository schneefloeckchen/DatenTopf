package datentopf

import org.codehaus.groovy.runtime.NullObject
import grails.util.*

class TopfTagLib {

  static namespace = 'topf'

  def formTextField = {attrs, body ->
  }


  /**
   * generates the head section of the individual pages
   * But seems not to work this way...
   */
  def head = {attrs, body ->
    out << '<meta name="layout" content="main" />'
    out << '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />'
    out << '<title>'
    out << g.message (code:"${attrs.titleCode}")
    out << '</title>'
  }
  
  //
  //  Buttons for dialogs
  //

  /** Creates a standard button header
   *  <topf:naviHeader buttons="['list', 'home', ..]" dbClass='' />
   *
  <div class="nav" role="navigation">
  <ul>
  <li><topf:homeButton/></li>
  <li><topf:logoutButton/></li>
  <li><topf:listButton dbClass="Address"/></li>
  </ul>
  </div>

   * */
  def naviHeader = {attrs ->
    log.info "params are ${attrs}"
    out << '<div class="nav" role="navigation">'
    out << '<ul>'
    def dbClass = attrs.dbClass
    def args = ['dbClass': dbClass]
    attrs.buttons.each { button ->
      out << '<li>'
      //      def extraContent = ""
      //      if (button == list) extraContent = " dbClass"
      out << this.invokeMethod("${button}Button", args)
      out << '</li>'
    }
    out << '</ul>'
    out << '</div>'
  }
  
  
  /** Creates a standard home button for the ui
   */
  def homeButton = {
    log.info "creating home button"
    out << """<a class="home" href="${createLink(uri: '/')}">"""
    out << g.message (code: "default.home.label")
    out << '</a>'
  }
  
  /** Creates a standard list button for the ui
   *  <topf:listButton dbClass='..' />
   */
  def listButton = {attrs, body ->
    out << g.link (class: "list", action: "list") {
      g.message (code: "default.list.label", args: [attrs.dbClass])
    }
  }
  
  /** Creates a standard create button for the ui
   */
  def createButton = {attrs, body ->
    out << g.link (class: "create", action: "create") {
      g.message (code: "default.create.label", args: [attrs.dbClass])
    }
  }
  
  /** Creates a standard edit button for the ui
   *@todo: Add better handling for id. E.g. provide object in call, get from there id plus dbClass.
   */
  def editButton = {attrs, body ->
    out << g.link (class: "edit", action: "edit") {
      g.message (code: "default.edit.label", args: [attrs.dbClass])
    }
  }
  
  /** Creates a standard logout button for the ui
   */
  def logoutButton = {
    out << g.link (action: 'logout', controller: 'home') {g.message (code: "default.logout.label")}
  }
  
  //
  // Components to enter data in dialogs, uses standard
  // GRAILS 2.x format (css) classes
  // naming:  dialogxxxxxx
  // attributes in tag are:
  //    instance:  the bean to work on, or to display as existing data
  //    field: field in bean to work with
  //    labelCode: code to fetch label text from message bundle
  //
  
  /**
   *  generates a text entry field like the generated ones.
   *  Usage:
   *  <topf:dialogField label=<labelCode> instance=<instance> field=<field> />
   *  labelCode: Code for label in the messages file
   *  instance: the current instance, must not be null!
   *  field: Name of field in bean 
   */
  def dialogField = { attrs ->
    log.info "${attrs}"
    def value = attrs.instance?."${attrs.field}"
    log.info "value= ${value}"
    createDialogLabel (attrs)
    out << g.textField (name:attrs.field, value: value)
    out << "</div>"
  }

  /**
   *  generates a password entry field like the generated ones.
   *  Usage:
   *  <topf:dialogField label=<labelCode> instance=<instance> field=<field> />
   *  labelCode: Code for label in tle messages file
   *  field: Name of field in bean 
   */
  def dialogPwdField = { attrs ->
    log.info "${attrs}"
    createDialogLabel (attrs)
    out << g.passwordField (name: attrs.field)
    out << "</div>"
  }
  
  /**
   * creates an checkbox including a label
   */
  def dialogCheckBox = { attrs ->
    log.info "checkBox instance is ${attrs.instance}"
    def value = attrs.instance?."${attrs.field}"
    createDialogLabel (attrs)
    out << g.checkBox (name:attrs.field, value: value)
    out << "</div>"
  }
  
  /**
   * <topf:dialogCombo labelCode=<labelCode> instance="instance" field="field" fromClass="" />
   */
  def dialogCombo = { attrs ->
    log.info "attrs are ${attrs}"
    createDialogLabel (attrs)
    def method = 'list'
    def field = attrs.field
    def clazz = grailsApplication.getDomainClass(attrs.fromClass).newInstance()
    def fromList = clazz.metaClass.invokeStaticMethod(clazz, method, null)
    def value = attrs.instance?."${attrs.field}"?.id
    out << g.select (id: field, name: "${field}.id",
      from: fromList, optionKey: 'id', class: "many-to-one")
  }

  /**
   *  generates a text entry field like the generated ones.
   *  Usage:
   *  <topf:dialogField label=<labelCode> instance=<instance> field=<field> />
   *  labelCode: Code for label in the messages file
   *  instance: the current instance, must not be null!
   *  field: Name of field in bean 
   */
  def dialogDateChooser = { attrs ->
    log.info "${attrs}"
    def value = attrs.instance?."${attrs.field}"
    log.info "value= ${value}"
    createDialogLabel (attrs)
    out << g.datePicker (name:attrs.field, value: value, precision:"day",
      relativeYears: [-100..1], default: null, noSelection: ['':'-Choose-'] )
    out << "</div>"
  }

  /**
   * Creates the standard save button in the UI, using the grails 2 css formats
   */
  def dialogSaveButton = {
    def value = g.message(code: "default.button.create.label")
    out << '<fieldset class="buttons">'
    out << g.submitButton (name:"create", class:"save", value: value)
    out << '</fieldset>'
  }
  
  /**
   *  Creates the edit and optional delete button for the show dialog
   *  <topf:dialogEditbutton id="" delete='yes/no' />
   *  id: The id of the object to edit or delete
   *  delete: If det to yes, the delete button will also be created. Default is yes
   */
  def dialogEditButton ={ attrs ->
    log.info "attrs are ${attrs}"
    out << "<g:form>"
    out << "<fieldset class='buttons'/>" 
    out <<  g.hiddenField (name: 'id', value: attrs.id)
    out <<  g.link (class: 'edit', action: 'edit', id: attrs.id) {
      g.message (code: "default.button.edit.label", default: "Editiere")
    }
    if (attrs?.delete != 'no') {
      out << g.actionSubmit (class: 'delete', action: 'delete',
        value: message(code: 'default.button.delete.label', default: 'Delete'))
    }
    out << "</fieldset>"
    out << "</g:form>"
  }

  /**
   * Create an add button, to add person, telefon number or such
   * To open the add dialog, to enter the data
   * 
   * <topf:dialogAddButton instance="" childDomain="" />
   * 
   * instance: to which we want to add an object
   * childDomain: class of the new object (defined as String)
   */
  def dialogAddButton = { attrs ->
    log.info "Creating add Button attrs are ${attrs}"
    log.info "Instance is ${attrs.instance.class}"
    out << '<fieldset class="buttons">'
    def params = [id: attrs.instance.id, version: attrs.instance.version]
    def domain = attrs.childDomain.capitalize()
    def action = "add${domain}"
    log.info "params are ${params}"    // uebergabeparameter ist eine Map!
    out << g.link (class: 'edit', params: params, action: action) {
      g.message (code: 'default.add.label', args: [domain])
    }
    out << '</fieldset>'
  }
  
  /**
   * Create an add button, to add person, telefon number or such
   * To start the save process for the new object
   * 
   * <topf:dialogAddSaveButton instance="" childDomain="" />
   * 
   * instance: to which we want to add an object
   * childDomain: class of the new object (defined as String)
   */
  def dialogAddSaveButton = { attrs ->
    log.info "Creating AddSave  Button"
    log.info "attrs are ${attrs}"
    out << '<fieldset class="buttons">'
    def domain = attrs.childDomain.capitalize()
    def action = "addSave${domain}"
    out << g.hiddenField (name: 'id', value: attrs.id)
    out << g.submitButton (name: action, class: 'save', value: message (code: 'default.add.label', args: [domain]))
    out << '</fieldset>'
  }
  
  /**
   * Displays flash error message and infos of errors in beans fields
   * <topf:dialogError instance="" /> 
   */
  def dialogError = {attrs ->
    log.info "Flash is ${flash}"
    if (flash?.message != null) {
      out << '<div class="message" role="status">'
      out << flash.message
      out << '</div>'
    }
    def instance = attrs.instance
    if (instance?.hasErrors()) {
      out << '<ul class="errors" role="alert">'
      instance.errors.each { err ->
        def allErrors = err.getAllErrors()            // this finally extracts the list of all errors
        allErrors.each {error ->
          out << '<li>'
          out << g.message (error: error)
          out << '</li>'
        }
      }
      out << '</ul>'
    }
  }
  
  /**
   * Function for the dialog clousures, creating start tag of div and the
   * label information for the UI Elememnt
   */
  private void createDialogLabel (attrs) {
    def clazz = "fieldcontain " + hasErrors(bean: attrs.instance, field: attrs.field, "error")
    out << '<div class="'+clazz+'">'
    out << "<label for=${attrs.field}>"
    out << g.message (code: attrs.labelCode)
    out << "</label>"
  }
  
  //
  //
  // Elements to display data plus label in standard Grails view
  
  /**
   * Displays a textfield
   * <topf:displayText instance="" field="" labelCode="" />
   */
  def displayText = { attrs ->
    createDisplayLabel (attrs.labelCode)
    out << '<span class="property-value">'
    out << attrs.instance?."${attrs.field}"
    out << '</span>'
    out << '</li>'
  }

  /**
   * displayDateTime displays standard date and time for a given field of an instance
   * <topf:displayDateTime instance="" field="" labelCode="" />
   */
  def displayDateTime = { attrs ->
    def date = attrs.instance?."${attrs.field}"
    log.info "Date is ${date}"
    createDisplayLabel (attrs.labelCode)
    out << '<span class="property-value">'
    out << topf.formatDateTime(date: date)
    out << '</span>'
    out << '</li>'
  }
  
  def displayBoolean = {attrs ->
    log.info "DisplayBoolean attrs are ${attrs}"
    createDisplayLabel(attrs.labelCode)
    out << '<span class="property-value">'
    def bool = attrs.instance?."${attrs.field}"
    out << g.formatBoolean(boolean: bool)
    out << '</span>'
    out << '</li>'
  }

  private void createDisplayLabel (def labelCode) {
    out << '<li class="fieldcontain">'
    out << '<span class="property-label">'
    out << g.message (code: labelCode)
    out << '</span>'
  }
    
  /**
   * Formatier standard for time and date display
   * Shows date and time as defined in messages.properties
   * feld default.date.format
   * 
   * <topf:formateDateTime date="" />
   * 
   * Probably extention to define the code for the message file
   */
  def formatDateTime = {attrs ->
    def date = attrs.date
    log.info "date is ${date?.class}"
    if (date == null)
    out << g.message (code: 'default.date.notset')
    else
    out << g.formatDate (formatName: 'default.date.format', date: date)
  }

  //
  // Elements to support creation of list tables for display (list view)
  // start all with 'table'
  //

  /**
   *  Creates table header vor list-view, includes the tags header and tr
   *  <topf:tableHeader domain="" fields=["",""] />
   *  domain and fields are used to create the codes for the message map
   *  codes in the map must be: <domain>.<field>.label
   *  field map to the property argument in sortableHeader
   */
  def tableHeader = {attrs ->
    def fields = attrs.fields
    out << '<thead>'
    out << '<tr><td></td>'                // Empty cell for buttons
    fields.each {field ->
      def code = "${attrs.domain}.${field}.label"
      def title = "${message(code: code)}" 
      out << g.sortableColumn(property: field, title: title)
    }
    out << '</tr>'
    out << '</thead>'
  }
  
  /**
   * Creates all rows for the table, Pagination not intended
   * 
   * <topf:tableBody instanceList="" fields=['f1', 'f2'] />
   * 
  <g:each in="${persInstanceList}" status="i" var="persInstance">
  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

   */
  def tableBody = { attrs ->
    log.info "Params are ${attrs}"
    out << '<tbody>'
    attrs.instanceList.eachWithIndex() {instance, i ->
      log.info "-- ${instance} : ${i}"
      def clazz = "${(i % 2) == 0 ? 'even' : 'odd'}"
      log.info "${clazz}"
      out << '<tr class="'+clazz+'">'
      out << '<td class="buttons">'
      out << g.link (class: 'show', action: 'show', id: instance.id) {
        g.message(code: 'default.button.show.label')
      }
      out << '</td>'
      attrs.fields.each {field ->
        out << '<td>'
        out << fieldValue(bean: instance, field: field)
        out << '</td>'
      }
      out << '</tr>'
    }
    out << '</tbody>'
  }
  /**
   * to create the columns for the table (list view), all must be Strings or booleans (true/false).
   * <topf:tableFields instance="" fields=['', ''] select='n'/>
   * select indicates the index in field (starting with 0), which will be used to
   * add <g.link> to enable loading of the object (creates link in tables)
   * 
   */
  def tableFields = {attrs ->
    log.info "attrs are ${attrs}"
    def select = null     // not set
    select = attrs?.select?.toInteger()
    def index = 0
    attrs.fields.each {field ->
      def link = false
      if (select == index) link = true
      out << '<td>'
      if (link) {
        out << g.link (action: 'show', id: attrs?.instance?.id) {
          out << fieldValue(bean: attrs.instance, field: field)
        }
      }
      else {
        out << fieldValue(bean: attrs.instance, field: field)
      }
      out << '</td>'
      index++
    }
  }
  
  def tableDatum = {attrs ->
    log.info "attrs are ${attrs}"
    attrs.fields.each {field ->
      out << '<td>'
      def date = attrs.instance?."${field}"
      out << topf.formatDateTime(date: date)
      out << '</td>'
    }    
  }
  
  //
  // Elements for show panels, where html-tables are used to structure the view
  // old style
  //
  
  /**
   * Creates one row for the display, incl. <tr> tags
   * <topf:viewRow instance=" fields="['f1', 'f2',..]" />
   * 
   * Label are with code  <instance.class>.<field>.label in message.properties
   * stored
   */

  def viewRow = {attrs ->
    log.info "attrs are ${attrs}"
    def instance = attrs.instance
    def instanceText = GrailsNameUtils.getShortName(instance.getClass()).toLowerCase()
    def fields = attrs.fields
    out << '<tr class="fieldcontain">'
    fields.each { field ->
      def value = instance?."${field}"
      def labelCode = "${instanceText}.${field}.label"
      out << '<td>'
      out << '<span class="property-label">'
      out << g.message (code: labelCode)
      out << '</span>'
      out << '</td>'
      out << '<td>'
      out << '<span class="property-value">'
      out << g.fieldValue (bean: instance, field: field)
      out << '</span>'
      out << '</td>'
    }
    out << "</tr>"
  }
  
  //
  //  def tst = {
  //    def stringWriter = new StringWriter()
  //    def mb = new groovy.xml.MarkupBuilder(stringWriter)
  //    
  //    mb.div {
  //      p ("Test")
  //    }
  //    out << stringWriter.toString()
  //  }
}
  
