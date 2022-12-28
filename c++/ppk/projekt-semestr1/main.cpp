//TODO
//- zmiana konceptu na projekt, uzycie pq ulatwi tworzenie sciezek
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
#include <queue>
#include <cmath>

//stworzenie grafu
typedef std::map<std::string, std::set<std::pair<std::string, double>>> Graph;
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

std::vector<std::string> construct_shortest_path(const std::map<std::string, std::string>& previous, const std::string& start, const std::string& end){
    std::vector<std::string> path;
    for (std::string v = end; v != start; v = previous.at(v)) {  // idzie od koncowego elementu w mapie previous od momentu az v != start
        path.push_back(v);
    }
    path.push_back(start);                                          // ostatnim brakujacym elementem jest pierwsze miasto wiec trzeba je dodac
    std::reverse(path.begin(), path.end());                // przez to ze dodawalismy elementy od konca to trzeba odwrocic vector i mamy poprawna sciezke
    return path;
}

std::pair<double, std::vector<std::string>> dijkstra(const Graph& graph, const std::string& start, const std::string& end){
    std::map<std::string, double> result;                   //wynik (dystans)
    std::map<std::string, std::string> previous;            //poprzednicy
    std::priority_queue<std::pair<double, std::string>> pq; //priorytet miast

    for (const auto& [vertex, edges] : graph) {  //kazde miasto bedzie mialo nieskonczona odleglosc bo tam jescze nie bylismy i nie wiemy jaki jest tam dystans
        result[vertex] = std::numeric_limits<double>::infinity();
    }

    result[start] = 0;                                      // do miasta startowego odleglosc jest rowna 0, bo sie w niej znajdujemy
    pq.emplace(0, start);                              // dodanie pierwszego elementu do kolejki

    while (!pq.empty()) {
        double distance = pq.top().first;                   // pobiera dystans pierwszego w kolejce elementu
        std::string vertex = pq.top().second;               // pobiera nazwe pierwszego miasta w kolejce
        pq.pop();                                           // usuwa pierwszy element w kolejce

        if (distance != result[vertex]) {                   // jezeli dystans dla tego miasta jest juz ustawiony to odwiedzilismy to miasto, wiec przechodzimy do poczatku petli
            continue;
        }

        if (vertex == end) {                                // jezeli osiagnelismy juz koncowy punkt to zwroc dystans i sciezke
            return {distance, construct_shortest_path(previous, start, end)};
        }

        for (const auto& [neighbor, weight] : graph.at(vertex)) { // dla kazdego sasiada wykonaj petle
            if (result[neighbor] > distance + weight) {                                    // jezeli wartosc sasiada jest wieksza od sumy odleglosci + odleglosci do sasiada wtedy wykonaj warunek ( tu jest sprawdzana najbardziej optymalna odleglosc )
                result[neighbor] = distance + weight;
                previous[neighbor] = vertex;
                pq.emplace(distance + weight, neighbor);                            // dodaj na poczatek kolejki ten element odleglosc i nazwe sasiedniego miasta
            }
        }
    }

    return {std::numeric_limits<double>::infinity(), {}}; // jezeli nie potrafi dotrzec do wyznaczonego punktu to zwraca nieskonczonosc i
}

int main(){
    auto graph = LoadFromFile("lista.txt");
    auto path_from_a_to_b = dijkstra(graph, "Katowice", "Poznan");
}