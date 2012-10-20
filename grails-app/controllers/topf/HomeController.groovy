package topf

import topf.security.User
class HomeController {

  def index() { 
    println "Home index"  
  }
  def loginPage() { // @todo: check, if already logged in. in this case go to mainDialog
    log.info session.getClass()
    log.info "User is ${session.user}"
    if (session.user) forward (action: 'mainDialog')
  }
  
  def login = {
    log.info "login ${params}"
    def user = User.findByName (params.user)
    if (user == null) {
      flash.message = message (code: 'loginPage.error.userNotFound', args: [params.user])
      forward (action: 'loginPage')
    } else {
      if (user.isPwdOk(params.password)) {
        session.user = user
        forward (action: 'mainDialog')
      } else {
        flash.message = message (code: 'loginPage.error.wrongPassword', args: [params.user])
        forward (action: 'loginPage')
      }
    }
  }
  
  /**
   * only used for development
   */
  def forceLogin = {
    session.user = new User(name: 'rene', superUser: true)
    forward (action: 'mainDialog')
  }
  
  def mainDialog = {
    
  }
  
  def catalogs = {
    log.info "Starting catalogs"
  }
  
  def logout = {
    session.user = null     // @todo: Verbessern
    forward (action: 'loginPage')
  }
}
