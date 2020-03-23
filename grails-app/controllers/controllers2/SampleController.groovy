package controllers2

import controller2.EmployeeCO
import org.springframework.web.multipart.MultipartFile

class SampleController {

    static defaultAction = "fileUpload"

    def index() {}

    def test() {
        Person p = new Person(params)
//        p.dob=  params.date('dob','dd-MMM-yyyy hh:mm:ss')
        render p.validate()
        render "<br>"
        render "p.hasErrors()>>>>>>>>"+p.hasErrors()
        render "<br>"
        render "p.errors.allErrors>>>>>>>>"+p.errors.allErrors
        render "<br>"
        render "p.errors.getFieldErrors('age')>>>>>>>>>"+p.errors.getFieldErrors('age')
        render "<br>"
        render p.properties
    }



    def intBinding(Integer intParam, String stringParam) {
        //http://localhost:8080/sample/intBinding?intParam=20&stringParam=Sahi
        Person person = new Person(name: stringParam, age: intParam)
//		params.date("dob", "dd-MM-yyyy")
//		println "person1.validate()>>>>>"+person1.validate()
//		Person person1 = new Person(params)
        render intParam
        render stringParam
        render "<br>"
        render person
    }

//	//autoBinding
//	def autoBinding() {
//        //http://localhost:8080/sample/autoBinding?intParam=20&stringParam=Sahi&dateParam=20-07-2019
//        render params.intParam
//		render params.stringParam
//		render params.dateParam
//	}

    //params magic
    def paramsConversion() {
//        http://localhost:8080/sample/paramsConversion?age=20&dob=20-Jan-2019&name=Prashant
        Integer age = params.int("age")
        String name = params.name
        Date dob = params.date("dob", "dd-MMM-yyyy")
        Person person = new Person(name: name, age: age, dob: dob)

        render name
        render age
        render dob ?: "Date is not in proper format"
        render "<br>"
        render person
    }

    def fetchList() {
//        http://localhost:8080/sample/fetchList?items=elem1&items=elem2&items=elem3
        List list = params.list("items")
        render list

    }

    //http://localhost:8080/sample/dataBindWithErrors?name=Sahi&employeeId=Rx-2&location=pune&age=0&dept=2&dob=15-Aug-1991
    def dataBindWithErrors() {
        println "------------------------------------ " + params
//        params.date("dob", "dd-MM-yyyy")
        Employee b = new Employee(params)
        println "b.validate()>>>>" + b.validate()
        println "b.hasErrors()>>>>" + b.hasErrors()
//        println "b.errors.allErrors>>>>" + b.errors.allErrors
        b.errors.allErrors.each{
            println it
        }
        println "b.errors.hasFieldErrors()>>>>" + b.errors.hasFieldErrors()
        println "b.errors.getFieldErrors()>>>>" + b.errors.getFieldErrors()
        println "b.errors.getFieldError('dob')>>>>" + b.errors.getFieldError("dob")
//        println "b.errors.getFieldError()>>>>" + b.errors.getFieldError("dept")
        if (b.hasErrors()) {
            println "The value ${b.errors.getFieldError('dob')}"
            if (b.errors.hasFieldErrors("dob")) {
                println b.errors.getFieldError("dob").rejectedValue
            }
        }

        render b.properties
    }

//        http://localhost:8080/sample/signleEndedAssociation?dept.id=1
    def signleEndedAssociation() {
        def b = new Employee(params)
        render b.properties
    }

    //http://localhost:8080/sample/multipleDomainBinding?dept.id=1&emp.name=Sahi&emp.age=25
    def multipleDomainBinding() {
        def b = new Employee(params)
        b.properties = params['emp']
        render b.properties
    }

    //http://localhost:8080/sample/usingCO?name=Sahi&employeeId=Rx-2&location=pune&age=2&dept=2
    def usingCO(EmployeeCO employeeCO) {
        println employeeCO.properties
        Employee employee = new Employee(employeeCO.properties)
        if (!employeeCO.validate()) {
            render employee.properties
            render "<br>"
            render "-------------------------------------"
            render employeeCO.errors
            render "<br>"
        } else {
            employee.save()
            render employee.properties
        }
    }

    def fileUpload() {

    }


    def gFileUpload() {
        MultipartFile myFile = params.myFile
        File file = new File("/Users/prashantsahi/Downloads/image/${myFile.originalFilename}")
//		render file.text
        render file.size()
//		render "<br>"
//		render myFile.inputStream.text
        render "<br>"
        render "${file.name} is uploaded sucessfully"
        render "<br>"
        render "Done!!!"
    }

    def download() {
        File file = new File("/Users/prashantsahi/Downloads/image/dummy_user.jpg")
        byte[] orderPDF = file.getBytes()
        response.setHeader("Content-disposition", "attachment; filename=" + file.name)
        response.contentLength = orderPDF.length
        response.outputStream << orderPDF
    }
}
