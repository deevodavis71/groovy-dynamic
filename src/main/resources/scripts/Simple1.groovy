import com.sjd.groovytest.ITestScript
import com.sjd.groovytest.Payload

class Test implements ITestScript {

    String getMessage(Payload p) {
        [1..10].each(){
            println it
        }
        String passed = p.toString ();
        p.setLatitude (8);
        p.setLongitude (9);
        return "Jello - passed lat/long = " + passed
    }
    
}