package controller2

import controllers2.Department
import grails.validation.Validateable

class EmployeeCO implements Validateable{
	String name
	String employeeId
	int age
	String location
	Date dob
	Department dept

	static constraints = {
		employeeId unique: true
		location nullable: true
		dob nullable: true
		age min: 1
	}
}
