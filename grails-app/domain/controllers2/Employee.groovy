package controllers2

class Employee {

    String name
    String employeeId
    int age
    String location
    Date dob
    static belongsTo = [dept:Department]

    static constraints = {
        employeeId unique: true
        location nullable: true
        dob nullable: true
    }

    String toString(){
        "${name} ($employeeId)"
    }
}
