//import groovy.transform.BaseScript
//@BaseScript MainScript mainScript

import com.sjd.groovytest.ITestScript
import com.sjd.groovytest.Payload

// println "$meaningOfLife" //works as expected

class TestDependency implements ITestScript {

    String getMessage(Payload p) {

		def ms = new MainScript ();
        return "Jello - " + ms.meaningOfLife + " " + ms.favColor () 
    }
    
}