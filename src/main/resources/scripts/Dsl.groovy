// http://mrhaki.blogspot.co.uk/2011/05/groovy-goodness-command-chain.html 

// http://mrhaki.blogspot.co.uk/2009/11/groovy-goodness-running-groovy-scripts.html
 
// Constants for tasks and clients.
enum Task { design, testing, developing }
enum Client { GroovyRoom, OfficeSpace }
 
// Supporting class to save work item info.
class WorkItem {
    Task task
    Client client
    Integer hours
}
 
// Support syntax 1.hour, 3.hours and so on.
Integer.metaClass.getHour = { -> delegate }
Integer.metaClass.getHours = { -> delegate }
 
// Import enum values as constants.
import static Task.*
import static Client.*
 
// List to save hours spent on tasks at
// different clients.
workList = []
  
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
 
// -----------------------------------
// DSL
// -----------------------------------
worked 2.hours on design at GroovyRoom
developed 3.hours at OfficeSpace
developed 1.hour at GroovyRoom
worked 4.hours on testing at GroovyRoom
 
 
// Test if workList is filled
// with correct data.
def total(condition) {
    workList.findAll(condition).sum { it.hours }
}
 
assert total({ it.client == GroovyRoom }).hours == 7
assert total({ it.client == OfficeSpace }).hours == 3
assert total({ it.task == developing }).hours == 4
assert total({ it.task == design }).hours == 2
assert total({ it.task == testing }).hours == 4