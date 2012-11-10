import topf.addy.Person
import topf.security.User
class BootStrap {

    def init = { servletContext ->
      if (User.count() == 0) {
        def u1 = new User(name: 'admin', pwd: 'adminadmin')
        u1.save()
      }
    }
    def destroy = {
    }
}
