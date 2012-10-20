import topf.addy.Person
class BootStrap {

    def init = { servletContext ->
      def p1 = new Person (firstName: 'Rene', lastName: 'Zillmann')
      p1.save()
      p1 = new Person (firstName: 'Klara', lastName: 'Zillmann')
      p1.save()
      
    }
    def destroy = {
    }
}
