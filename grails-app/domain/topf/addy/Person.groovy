package topf.addy

import topf.catalog.*
import topf.addy.tele.*

class Person {

  Salut salutation
  Title title
  String firstName
  String lastName
  Address address
  
  static hasMany = [telNumbers: TelNumber]
  
  static constraints = {
    salutation (nullable: true)
    title (nullable: true)
    firstName (blank: true)
    lastName (blank: true)
    address (nullable: true)
  }
  
  String toString() {
    "${lastName}, ${firstName}"
  }
}
