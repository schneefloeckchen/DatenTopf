package topf.addy

class AddressController {

  def scaffold = true
    
  def create = {
    log.info "creating address"
  }
  
  def addPerson = {
    log.info "Adding person to address"
    log.info "Params are ${params}"
    [id: params.id, version: params.version]
  }
  
  def addSavePerson = {
    log.info "Adding and saving person to address"
    log.info "Params are ${params}"
    def addy = Address.get(params.id)
    if (addy == null) {
      flash.message = message (code: 'default.not.found.message', args:['Address', params.id])
      forward (action: 'show', id: params.id)
    } else {
      def person = new Person(params)
      if (!person.validate()) {
        flash.message = message(code: 'default.not.created.message', args: ['Address'])
        render (view: 'addPerson', model: [id: params.id, version: params.version, personInstance: person])
      } else {
        Address.withTransaction { transactionStatus ->
          if (!person.save()) {
            flash.message = message(code: 'default.not.created.message', args: ['Address'])
            transactionStatus.setRollbackOnly()
          } else {
            addy.addToPersons(person)
          }
        }
        forward (action: 'show', id: params.id)
      }
    }
  }
}
