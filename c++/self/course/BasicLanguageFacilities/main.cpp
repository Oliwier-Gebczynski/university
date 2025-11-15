// Paradygnaty C++ "Object-oriented (OOP), imperative (opis klasyczny co komputer ma zrobic), generic (pracuje z roznymi typami danych)".
// Smart pointer to obiekt (klasa), który w środku trzyma ten surowy wskaźnik. Kiedy ten obiekt smart pointera zostaje zniszczony (np. wychodzi poza zakres funkcji), automatycznie woła delete na trzymanym wskaźniku.
// Kroki budowania: Preprocessing -> Compilation -> Linking
// Arithmetic -> Integral & floating point
// Integral -> bool, char, short, int, long
// Floating point -> float & double
/*
 * Datatype size:
 * 1 byte - bool, char
 * 2 byte - wchar_t, short
 * 4 byte - int, long, float
 * 8 byte - long long, double, long double
 *
 *
 *
 *
 */

#include <iostream>
#include <string>

int Add(int *x, int *y);
void AddVal(int *x, int *y, int *result);
void AddRef(int x, int y, int &result);
void Swap(int *x, int *y);
inline int Square(int x);
void Print(std::string text);

int main() {
    int i = 10;
    int &ref = i;

    i = 6;

    std::cout << i << std::endl;
    std::cout << ref << std::endl;

    int x{6};
    int y{7};

    int *xptr = &x;
    int *yptr = &y;
    int *result;

    int sum = Add(xptr, yptr);

    AddVal(xptr, yptr, result);

    //direct initialization
    //int arr[2] = {6,7};
    int arr[2]{6,7};

    std::cout << sum << std::endl;
    std::cout << *result << std::endl;

    std::cout << "Before swap: " << *yptr << *xptr << std::endl;
    Swap(&*xptr, &*yptr);
    std::cout << "After swap: " << *yptr << *xptr << std::endl;

    int added = 0;
    int &addedRef = added;


    std::cout << "Before add: " << added << std::endl;
    AddRef(x, y, added);
    std::cout << "After add: " << added << std::endl;

    int squared = Square(arr[0] + 4);
    std::cout << "Squared: " << squared << std::endl;

    return 0;
}

int Add(int *x, int *y) {
    return  *x+*y;
}

void AddVal(int *x, int *y, int *result) {
    *result = *x+*y;
}

void AddRef(int x, int y, int &result) {
    int sum = x+y;
    result = sum;
}

void Swap(int *x, int *y) {
    int temp = *x;
    *x=*y;
    *y=temp;
}

inline int Square(int x) {
    return x * x;
}


void Print(std::string text) {
    std::cout << "Your text: " << text << std::endl;
}

