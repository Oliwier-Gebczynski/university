#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>

struct distanceBetweenCities{
    std::string aCity;
    std::string bCity;
    int distance;
};


std::vector<distanceBetweenCities> readFile(){

    std::vector<distanceBetweenCities> result;

    std::string fileName;
    std::cout << "Wpisz nazwę pliku ( z rozszerzeniem .txt ): " << std::endl;
    std::cin >> fileName;

    std::fstream in(fileName);

    if(in){
        std::string line;

        while (std::getline(in, line))
        {
            if (line.length() == 0) break;
            
            std::stringstream ss(line);
            std::string aCity, bCity, distance;

            std::getline(ss, aCity, ' ');
            std::getline(ss, bCity, ' ');
            std::getline(ss, distance, ' ');

            result.push_back({aCity, bCity, stoi(distance)});
        }
        in.close();
    }
    return result;
}

void show(const distanceBetweenCities& dbc){ 
    std::cout << "City A: " << dbc.aCity << std::endl;
    std::cout << "City B: " << dbc.bCity << std::endl;
    std::cout << "Distance: " << dbc.distance << std::endl;
}

void show2(const std::vector<distanceBetweenCities>& dbc){
    for (const auto& el : dbc){
        show(el);
    }
}

int main(){
    distanceBetweenCities dbc;

    auto z = readFile();

    show2(z);
}