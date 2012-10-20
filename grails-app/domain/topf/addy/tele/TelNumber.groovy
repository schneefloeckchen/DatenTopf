package topf.addy.tele
import topf.catalog.*

/**
 * Telephone Numbers are stored here. 
 * 
 * @author rzi
 *
 */
class TelNumber {
	
	Country country
	TelType telType = null     // buss or private etc.
	Integer areaCode
	Integer telNumber
	String remark = ""
	TelProvider provider = null
	
    static constraints = {
		country (nullable: true)
		telType (nullable: true)
		areaCode (min: 1)
		telNumber (min: 1)
		remark (blank: true)
		provider (nullable: true)
    }
	
	
}
