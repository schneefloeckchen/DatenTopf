package topf.addy

import topf.catalog.*
import topf.addy.tele.*

class Person {

  Salut salutation
  Title title
  String firstName
  String lastName
  Address address
  String email = null
  Date birthday = null
  
  static hasMany = [telNumbers: TelNumber]
  
  static transients = ['fullName']
  
  static constraints = {
    salutation (nullable: true)
    title (nullable: true)
    firstName (blank: true)
    lastName (blank: true)
    address (nullable: true)
    email (nullable: true)
    birthday (nullable: true)
  }
  
  String getFullName() {
    "${firstName} ${lastName}"
  }
  
  String toString() {
    "${lastName}, ${firstName}"
  }
}
