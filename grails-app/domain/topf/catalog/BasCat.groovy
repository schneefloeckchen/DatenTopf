package topf.catalog

/**
 *  All catalogs extend this class, expect that most catalogs
 *  do not need more code.
 *  
 */

class BasCat {

  String name = ""
  String remark = ""
  
  static constraints = {
    name (blank: false)
    remark (blank: true)
  }
  
  String toString() {name}

}
