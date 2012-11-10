package topf.addy

import topf.catalog.*
import topf.addy.tele.*

class Address {

  String name = ""                // Short name of the address, like grandparents
  Country country = null          // country code here stored as well
  String street = ""
  String streetNumber = ""          // Might have some extension
  String addInfo = ""             // For the street adress
  String town = ""
  String zip = ""
  static hasMany = [telNumbers: TelNumber, persons: Person]

  static transients = ['fullTown', 'fullStreet']
  
  static constraints = {
    name (blank: true)
    country (nullable: true)
    //		telNumbers (nullable: true)
  }
   
  String getFullTown() {
    def countryText = country ? "(${country})" : ''
    "${zip} ${town} ${countryText}"
  }
  
  String getFullStreet() {
    "${street} ${streetNumber}"
  }
  
  String toString() {
    name
  }
}
