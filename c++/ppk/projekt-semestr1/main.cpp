//TODO
//- poprawic wpisywanie danych np: zeby miasto wpisane z malej litery tez dzialalo, jezeli zle wpisane miasto to komunikat o bledzie
#include "main.h"

int main(int argc, char* argv[]){
    std::vector<std::string> data = userData(argc, argv);

    if (data.empty()){
        return 0;
    }

    auto graph = LoadFromFile(data[0]);

    saveToFile(data[1], graph, data[2]);
    return 0;
}