package topf.security
import topf.addy.*

/**
 * For access control, simple version
 * @author rzi
 *
 */

class User {

  String name
  String encPwd               // Encrypted password
  Boolean superUser = false
  Boolean disabled = false    // if true, the user cannot login any more
  Person person = null        // Optional link to person data
  Date created                // Date, when the user was created in database
  Date updated = null
  Date lastLogin = null

  static constraints = {
    name (blank: false, unique: true)
    encPwd (blank: true, nullable: true)
    person (nullable: true)
    created (nullable: true)
    updated (nullable: true)
    lastLogin (nullable: true)
  }
  static transients =['pwd']
	
  def beforeInsert() {
    log.info "Setting dates for creation of user"
    created = new Date()
    beforeUpdate()
  }
  
  def beforeUpdate() {
    updated = new Date()
  }
  
  void setPwd (String password) {
    log.info "password in is ${password}"
    encPwd = password.encodeAsSHA256()
    log.info "encrypted is ${encPwd}"
  }
  /**
   * Returns true, if the password is ok, and the user
   * can be authorized.
   * 
   * @param pwd password to check, not encrypted.
   * @return true, if password is ok, otherwise false
   */
  Boolean isPwdOk (String pwdTst) {
    def tp = pwdTst.encodeAsSHA256()
    if (tp.equals(encPwd)) return true
    else
    return false
  }
  
  String toString() {
    name
  }
}
