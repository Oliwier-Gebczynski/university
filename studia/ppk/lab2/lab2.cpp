#include <iostream>
#include <cmath>

int main(){
    // int a, b;

    // std::cout << "Wpisz a: " << std::endl;
    // std::cin >> a;

    // std::cout << "Wpisz b: " << std::endl;
    // std::cin >> b;

    // if (b != 0){
    //     std::cout << "a / b = " << a/b << std::endl;
    // }
    // else{
    //     std::cout << "Nie można dzielić przez 0!" << std::endl;
    // }

    // int max = a > b ? a : b;

    // std::cout << "Max " << max << std::endl;
    // std::cout << "Min " << (a < b ? a : b) << std::endl;

    double a, b, c;

    std::cout << "wpisz a: " ;
    std::cin >> a;

    std::cout << "wpisz b: " ;
    std::cin >> b;

    std::cout << "wpisz c: " ;
    std::cin >> c;

    if (a>0 and b>0 and c>0){
        if ((a + b > c) or (b + c > a) or (a + c > b)){
            double p = (a+b+c) / 2;

            double pole = sqrt(p*(p-a)*(p-b)*(p-c));

            std::cout << "Pole trojkata wynosi " << pole;
        }else{
            std::cout << "To nie jest trójkąt" << std::endl;
        }

    }else{
        std::cout << "To nie jest trójkąt" << std::endl;
    }

}