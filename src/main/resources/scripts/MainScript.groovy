// Constants for tasks and clients.
enum Task { design, testing, developing }
enum Client { GroovyRoom, OfficeSpace }
	
// Import enum values as constants.
import static Task.*
import static Client.*
	
// Support syntax 1.hour, 3.hours and so on.
Integer.metaClass.getHour = { -> delegate }
Integer.metaClass.getHours = { -> delegate }
	
// List to save hours spent on tasks at
// different clients.
workList = []

// Supporting class to save work item info.
class WorkItem {
    Task task
    Client client
    Integer hours
}
  
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

worked 2.hours on design at GroovyRoom

class MainScript {

    def meaningOfLife = 42
    
    def favColor () {
    
    		
    
    		return "white";
    }
}