package scripts

abstract class Employee {
    Integer salary = 0

    def getInfo() {
        "Salary: ${salary}"
    }

    abstract String report()
}

class Tester extends Employee {

    private testingFrameworks = []

    void runTests() {
        println "Testing..."
    }

    @Override
    String getInfo() {
        super.getInfo() + ", frameworks: ${testingFrameworks}"
    }

    @Override
    String report() {
        return null
    }
}

class Developer extends Employee {

    void makeCoffe() {
        println "Please..."
    }

    @Override
    String report() {
        return null
    }
}


void printInfo(Employee ... employees) {
    employees.each { println it.info }
}


Employee tester = new Tester()
Employee developer = new Developer()
Tester testr2 = (Employee) tester


println tester.getInfo()

println developer.getInfo()