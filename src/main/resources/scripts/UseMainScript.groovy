import com.sjd.groovytest.ITestScript
import com.sjd.groovytest.Payload

// Import enum values as constants.
import static Task.*
import static Client.*

class TestDependency extends MainScript implements ITestScript {

    String getMessage(Payload p) {
				
		worked 2.hours on design at GroovyRoom
		developed 3.hours at OfficeSpace
		developed 1.hour at GroovyRoom
		worked 4.hours on testing at GroovyRoom
		
        return "Jello - " + meaningOfLife + " " + favColor () 
    }
    
}