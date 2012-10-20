package topf.catalog

/**
 * Telco provider for mobile and landline
 * 
 * @author rz
 *
 */
class TelProvider extends BasCat {

	Boolean mobile = false;   // probably the provider supports both
	Boolean landline = false  // media
	
    static constraints = {
    }
}
