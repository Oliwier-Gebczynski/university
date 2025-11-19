#include <iostream>

//Abstrakcja - ukrywanie szczegolow implementacji, pokazywanie tylko niezbedznych informacji
//Enkapsulacja - mechanizm laczenia danych i metod w jedna calosc, takze kontrolowanie dostepu do danych (private, protected, public)
//Dziedziczenie - nowa klasa moze przejac własności i zachowane metody istniejacej juz klasy
//Polimorfizm - przetwarzanie obiektow roznego typu w ujednolicony sposob 
// statyczny - przeciazenie funkcji, szablony
// dynamiczny - funkcje wirtualne i wskazniki/referencje do klas bazowych,  

// copy constructor 
//1. Czym jest: Copy constructor to specjalna metoda używana do tworzenia nowego obiektu na podstawie istniejącego obiektu tej samej klasy (jest to jak tworzenie idealnej duplikaty).
//2. Kiedy jest używany: Kompilator wywołuje go automatycznie, gdy inicjalizujesz nowy obiekt na podstawie starego lub gdy przekazujesz obiekt do funkcji.
//3. Po co: Jest niezbędny, gdy Twój obiekt zarządza zasobami (np. pamięcią), aby zapewnić, że nowy obiekt otrzyma własne, niezależne kopie tych zasobów, a nie tylko wskazywał na pamięć oryginału.

// Rule of 3,  all should be defined if a user implements any of them. 
// Destructor - will free the resource
// Copy contructor - will perform a deep copy
// Copy assignment operation - will also perform a deep copy

// Delegating constructors (C++11) - pozwala jednemu konstruktorowi wywolac inny konstruktor tej samej klasy

class Car{
private:
    float fuel;
    float speed;
    int passengers;
    static int totalCount;
public:
    Car():Car(0){
        std::cout << "Car()" << std::endl;
    };
    Car(float amount):Car(amount,0){
        std::cout << "Car(float)" << std::endl;
    };
    Car(float amount, int pass){
        std::cout << "Car(float, int)" << std::endl;
        ++totalCount;
        fuel = amount;
        speed = 0;
        passengers = pass;
    };
    ~Car(){
        --totalCount;
        std::cout << "~Car()" << std::endl;
    };
};

int Car::totalCount = 0;

// Default and deleted functions
// It works only for destructor, copy constructor and assignment operator
// Key words: default, delete
 
class Integer{
private:
    int m_value{0};
public:
    Integer() = default;
    // Integer(){
    //     m_value = 0;
    // }
    Integer(int value){
        m_value = value;
    }
    Integer(const Integer&) = default;    //just copy object 
    // Integer(const Integer&) = delete;      //dont allow to copy object error: call to deleted constructor of 'Integer'

    void SetValue(int val){
        m_value = val;
    }

    void SetValue(float val) = delete;
};


int main(){
    std::cout << "It works" << std::endl;

    // //only get adress of p1, shallow copy
    // int *p1 = new int(5);
    // int *p2 = p1;

    // //copy value drom p1, deep copy
    // int *p3 = new int(*p1);

    //Car bmw;

    Integer i1;
    Integer i2(i1);

    return 0;
}


