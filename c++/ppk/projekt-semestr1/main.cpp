//TODO
//- dokonczyc tworzenie sciezki
//- ogarnac plik .h
//- zrobic wlaczenie z terminalu -i [plik wejsciowy] -o [plik wyjsciowy] -s [nazwa miasta startowego]
//- doxygen

#include <iostream>
#include <map>
#include <set>
#include <fstream>
#include <list>
#include <string>
#include <sstream>
#include <algorithm>
#include <vector>
#include <limits>

//stworzenie grafu
typedef std::map<std::string, std::set<std::pair<std::string, double>>> Graph;
typedef std::map<std::vector<std::string>, double> Result;
//wczytywanie z pliku do grafu

Graph LoadFromFile(const std::string& fileName) {
    Graph graph;                //zainicjowanie grafu
    std::fstream in(fileName); //otworzenie strumienia z plikiem

    if (in) {
        std::string line;
        while (std::getline(in, line)) {
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

void createPath(Result& rs, std::string node, std::string firstElement, double distance){
    std::vector<std::string> path;
    for (auto& item : rs) {
        std::vector<std::string> pt = item.first;

        if (pt.back() == node){
            for(auto& element : pt){
                path.push_back(element);
            }
            path.push_back(firstElement);
        }
    }
    rs.insert(std::make_pair(path, distance));
}

std::map<std::string, double> Dijkstra(const Graph& graph, std::string node) {                             //deklarujemy funkcje, node to punkt poczatkowy
    Result rs;
    std::map<std::string, double > result;                                                               //będziemy zwracać potem result, który bedzie mapą
    std::set<std::string> nodes;
    int index = 0;

    for (const auto& el : graph) {                                                                      //dla każdego elementu w grafie przypisuje wartośc inf
        result[ el.first ] = std::numeric_limits<double>::infinity();
    }
    if (graph.find(node) == graph.end()) return result;                                                 //WARUNEK KOŃCA
    result[node] = 0.0;                                                                                 //dla węzła początkowego
    while (true) {
        for (const auto& el : graph.at(node)) {
            if (result[el.first] > result[node] + el.second) {
                double distance = result[node] + el.second;
                result[el.first] = distance;
                nodes.insert(el.first);

                createPath(rs, node, el.first, distance);
            }
        }

        if (nodes.empty()) break;
        node = *std::min_element(nodes.begin(), nodes.end(), [&result](const auto& l, const auto& r) {return result[l] < result[r]; });  //zmienia node na miasto z najmiejsza iloscia kilometrow do niego
        nodes.erase(node);
    }
    return result;
}

int main(){
    auto graph = LoadFromFile("lista.txt");
    auto result = Dijkstra(graph, "Katowice");
}