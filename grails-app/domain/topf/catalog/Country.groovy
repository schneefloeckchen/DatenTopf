package topf.catalog

/**
 * Countries for the addresses, The countries name is
 * inherited from BasCat
 * 
 * @author rz
 *
 */
class Country extends BasCat {

	Integer countryCode = null;   // for telephon numbers, no leading zeroes
	
    static constraints = {
		countryCode ( min: 1)
    }
}
