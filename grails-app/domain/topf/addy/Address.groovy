package topf.addy

import topf.catalog.*
import topf.addy.tele.*

class Address {

	String name = ""                // Short name of the address, like grandparents
	Country country = null          // country code here stored as well
	String street = ""
	String streetNume = ""          // Might have some extension
	String addInfo = ""             // For the street adress
	String town = ""
	String postCode = ""
	static hasMany = [telNumbers: TelNumber]
	
    static constraints = {
		name (blank: true)
		country (nullable: true)
//		telNumbers (nullable: true)
    }
}
