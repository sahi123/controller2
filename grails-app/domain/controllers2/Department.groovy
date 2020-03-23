package controllers2

class Department {

	String name

	static hasMany = [employees:Employee]

	static constraints = {
	}

	String toString(){
		name
	}
}
