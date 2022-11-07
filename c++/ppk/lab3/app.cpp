#include <iostream>
#include <vector>
#include <algorithm>

void p1(){
    const int maxSize = 3;
    double tab[maxSize];

    for( size_t i = 0; i < maxSize; i++){
        std::cout << "tab[" << i << "]"<< " = ";
        std::cin >> tab[i];
    }

    std::cout << "[ ";
    for( size_t i = 0; i < maxSize; i++){
        std::cout << tab[i] << " ";
    }
    std::cout << "]";
}

void p2(){
    int size;
    
    std::cout << "size = ";
    std::cin >> size;

    double* tab = new double[size];

    for( size_t i = 0; i < size; i++){
        std::cout << "tab[" << i << "]"<< " = ";
        std::cin >> tab[i];
    }

    std::cout << "[ ";
    for( size_t i = 0; i < size; i++){
        std::cout << tab[i] << " ";
    }
    std::cout << "]";

    delete[] tab;
}

void p3(){
    size_t size;
    
    std::cout << "size = ";
    std::cin >> size;

    std::vector<double> tab(size);

    for( size_t i = 0; i < size; i++){
        std::cout << "tab[" << i << "]"<< " = ";
        std::cin >> tab[i];
    }

    std::cout << "[ ";
    for( size_t i = 0; i < size; i++){
        std::cout << tab[i] << " ";
    }
    std::cout << "]";
}

void p4(){
    size_t size;
    
    std::cout << "size = ";
    std::cin >> size;

    std::vector<double> tab(size);

    size_t i = 0;

    for( auto& el : tab){
        std::cout << "tab[" << i++ << "]"<< " = ";
        std::cin >> el;
    }

    std::cout << "[ ";
    for( const auto el : tab){
        std::cout << el << " ";
    }
    std::cout << "]";
}

void p5(){
    size_t size;
    
    std::cout << "size = ";
    std::cin >> size;

    std::vector<double> tab(size);

    size_t i = 0;

    for( auto it = tab.begin(); it != tab.end(); ++it){
        std::cout << "tab[" << i++ << "]"<< " = ";
        std::cin >> *it;
    }

    std::cout << "Bez sortowania : [ ";
    for( auto it = tab.begin(); it != tab.end(); ++it){
        std::cout << *it << " ";
    }
    std::cout << "]" << std::endl;

    //sortowanie 
     std::sort(tab.begin(), tab.end());

     std::cout << "Posortowane : [ ";
    for( auto it = tab.begin(); it != tab.end(); ++it){
        std::cout << *it << " ";
    }
    std::cout << "]";
}

void zadanie_1(){
    int size = 0; 
    
    while ( size <= 0)
    {
        std::cout << "Wpisz rozmiar: ";
        std::cin >> size;
    }

    std::vector<double> tab(size);

    size_t i = 0;
    double max, min;
    int maxindex = 0, minindex = 0;
    for ( auto& el : tab){
        std::cout << ++i << ". ";
        std::cin >> el;

        if (el > max){
            max = el;
            maxindex = i - 1;
        }else if(el < min){
            min = el;
            minindex = i - 1;
        }
    }

    std::cout << "[ ";
    for( const auto el : tab){
        std::cout << el << " ";
    }
    std::cout << "]";

    std::cout << "Najmniejsza liczba " << min << " jest na miejscu " << minindex << std::endl;
    std::cout << "Najwieksza liczba " << max << " jest na miejscu " << maxindex << std::endl;
}

int main(){
    // p1();
    // p2();
    // p3();
    // p4();
    // p5();
    zadanie_1();
}