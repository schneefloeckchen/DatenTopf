package topf.security



import grails.test.mixin.*
import org.junit.*
import org.codehaus.groovy.grails.plugins.codecs.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
@Mock(User)
@Mock(SHA256Codec)

class UserTests {

    void testPwdCheck() {
      def user = new User(name:'TU', pwd: '123')
	  println "encrypted ${user.encPwd}"
	  assertTrue user.isPwdOk ('123')
	  assertFalse user.isPwdOk ('1234')
    }
}
