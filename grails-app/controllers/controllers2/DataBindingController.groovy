package controllers2

class DataBindingController {

    def implicitConstructor() {
//        http://localhost:8080/dataBinding/implicitConstructor?name=sahi&age=27&employeeId=35&dept.id=1
        println "--------------------- "+params
        Employee employee = new Employee(params)
        employee.save(flush: true)
        render employee.properties
    }

    def usingProperties() {
//        http://localhost:8080/dataBinding/usingProperties?name=Amit&age=30&employeeId=Rx-022
        Employee employee = Employee.get(1)
        render "-----------------before"
        render employee.properties
        render "<br>"
        employee.properties = params
        render "-----------------after"
        render employee.properties
    }

    def bindDataAction() {
        Employee employee = Employee.get(1)
        render "-----------------before"
        render employee.properties
        render "<br>"
        bindData(employee, params, [exclude: ["name", "employeeId"]])
        render "-----------------after"
        render employee.properties
    }

    def bindOnlyInclusions(){
//        http://localhost:8080/dataBinding/bindOnlyInclusions?name=Sahi&employeeId=19&age=35&location=pune
        Employee employee = Employee.get(1)
        render "-----------------before"
        render employee.properties
        render "<br>"
//        bindData(employee, params, [include: ["location", "age"]])
        bindData(employee, params, [exclude: ["age","location"]])
        render "-----------------after"
        render employee.properties
    }

}
