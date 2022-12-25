#include <iostream>
#include <map>
#include <set>
#include <fstream>
#include <list>
#include <string>
#include <sstream>
#include <algorithm>
#include <climits>
#include <vector>

//stworzenie grafu
typedef std::map<std::string, std::set<std::pair<std::string, double>>> Graph;

//wczytywanie z pliku do grafu

Graph LoadFromFile(const std::string& fileName){
    Graph graph;                //zainicjowanie grafu
    std::ifstream in(fileName); //otworzenie strumienia z plikiem 
    if(in){
        std::string line;   
        while (std::getline(in,line)){
            std::stringstream ss(line);  //pobierz linie
            std::string node1, node2;
            double length;

            if (!(ss >> node1)) continue;   //continue przerywa bieżączy krok petli
            if (!(ss >> node2)) continue;
            if (!(ss >> length)) continue;
            graph[node1].insert({node2, length});   //np do miasta Gdynia dodaje miasto sopot i jego odleglosc np 5km
            graph[node2].insert({node1, length});   //a tu do miasta Sopot dodaje miasto Gdynia i odleglosc 5km 
        }
        in.close();
    }
    return graph;
}

int main(){
    auto graph = LoadFromFile("lista.txt");

}