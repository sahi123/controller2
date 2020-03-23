package controllers2

class Person {

    String name
    Integer age
    Date dob

    static constraints = {
        dob nullable: true
    }

    String toString() {
        "[Name: $name, age: $age, dob: $dob]"
    }
}
