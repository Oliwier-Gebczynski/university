#include <iostream> //cin cout (input/output stream)
#include <cmath>
#include <conio.h>

int main(){
    double a; //, b;
    std::cout << "a = ";
    std::cin >> a;

    // std::cout << "b = ";
    // std::cin >> b;

    // std::cout << "a + b = " << a+b << std::endl;
    // std::cout << "a - b = " << a-b << std::endl;
    // std::cout << "a * b = " << a*b << std::endl;
    // std::cout << "a / b = " << a/b << std::endl;
    // std::cout << "a ^ b = " << pow(a,b) << std::endl;
    // std::cout << "sqrt(a) =" << sqrt(a) << std::endl;
    // std::cout << "sqrt(b) ="<< sqrt(b) << std::endl;
    // std::cout << "a%b ="<< a%b << std::endl;

    //cwiczenie 2 zadanie 1
    // double suma = 0, min = a, max = a;
    // int ilosc_wprowadzonych = 0;

    // while(a != 0){
    //     std::cout << min;
    //     suma += a;
    //     ilosc_wprowadzonych += 1;
        
    //     if (a > max)
    //     {
    //         max = a;
    //     }

    //     if (a < min)
    //     {
    //         min = a;
    //     }
        
    //     std::cin >> a;
    // }

    // std::cout << "Suma          = " << suma << std::endl;
    // std::cout << "Srednia       = " << suma / (ilosc_wprowadzonych) << std::endl;
    // std::cout << "Min           = " << min << std::endl;
    // std::cout << "Max           = " << max << std::endl;

    //return 0;
    // cwiczenie 2 zadanie 2

    char samogloski[12] = {'a','e','i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y'};
    char litera = _getche();
    if (litera == 'Q' && litera == 'q'){
        return 0;
    }
}