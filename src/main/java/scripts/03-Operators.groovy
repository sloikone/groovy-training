package scripts
// Elvis operator

def value

println value

if (value != null) {
    println "Value: ${value}"
} else {
    println "Brak wartości"
}

println value != null ? "Value: ${value}" : "Brak wartości"

println value ?: "Brak wartości"

// Safe navigation, direct field access, method reference

class Person {
    private String name
    String getName() {
        println "Get name"
        return name
    }
    void sayHello() {
        println "Hi!"
    }
}

def person = new Person()

println person.@name?.contains('A')

def sayHelloMethod = person.&sayHello
sayHelloMethod()
sayHelloMethod.call()

// Spread operator

class Car {
    String make
    String model
}

def cars = [
        new Car(make: 'Ford', model: 'Focus'),
        new Car(make: 'Skoda')
]

println cars*.model

class MathUtils {

    Integer sum(Integer x, Integer y) {
        x + y
    }

}

def mathUtils = new MathUtils()

println mathUtils.sum(2, 3);

def numbers = [2]

println mathUtils.sum(*numbers, 4);

def otherNumbers = [2, 5, 7, *numbers]