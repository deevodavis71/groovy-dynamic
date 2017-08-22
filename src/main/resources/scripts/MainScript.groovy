// Constants for tasks and clients.
enum Task { design, testing, developing }
enum Client { GroovyRoom, OfficeSpace }

// Supporting class to save work item info.
class WorkItem {
    Task task
    Client client
    Integer hours
}

// Import enum values as constants.
import static Task.*
import static Client.*

class MainScript {

	MainScript () {
		setupMeta ()
	}

	// List to save hours spent on tasks at
	// different clients.
	def workList = []
	
	def worked(Integer hours) {
	    ['on': { Task task ->
	        ['at': { Client client ->
	            workList << new WorkItem(task: task, client: client, hours: hours)
	        }]
	    }]
	}
	 
	def developed(Integer hours) {
	    ['at': { Client client ->
	        workList << new WorkItem(task: developing, client: client, hours: hours)
	    }]
	}

    def meaningOfLife = 42
    
    def favColor () {    		
    		return "white";
    }
    
    def setupMeta () {

    		// Support syntax 1.hour, 3.hours and so on.
		Integer.metaClass.getHour = { -> delegate }
		Integer.metaClass.getHours = { -> delegate } 
		   
    }
}