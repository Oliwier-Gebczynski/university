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
#include <cctype>
#pragma once

/**
    *@brief Definicja Grafu. Składa się on z mapy, której kluczem jest nazwa miasta. Kolejną częścią jest set składający się z pary, która przechowuje wszystkie połączenia wraz z jej odległościami do danego miasta.

*/
typedef std::map<std::string, std::set<std::pair<std::string, double>>> Graph;

/**
 * @brief Funkcja odpowiedzialna za wczytanie grafu z pliku tekstowego. Funkcja otwiera plik o nazwie podanej w argumencie, następnie odczytuje każdy wiersz i za pomocą operatora ">>" wczytuje informacje o wierzchołkach i długości krawędzi, które następnie są dodawane do grafu.
 * @param fileName Nazwa pliku wejściowego
 * @return Załadowany graf danymi z pliku wejściowego.
 */
Graph LoadFromFile(const std::string& fileName);

/**
 * @brief Funkcja odpowiedzialna za tworzenie wektora zawierającego najkrótszą ścieżkę między dwoma wierzchołkami w grafie. Funkcja działa poprzez przeglądanie mapy "previous" i tworzenie wektora zawierającego wierzchołki od końcowego do startowego. Następnie wektor jest odwracany i zwracany jako wynik funkcji.
 * @param previous Mapa która zawiera informacje o poprzedniku dla każdego wierzchołka w grafie
 * @param start Nazwa początkowego wierzchołka w grafie
 * @param end Nazwa końcowego wierzchołka w grafie
 * @return Vector z miastami w dobrej kolejności.
 */
std::vector<std::string> ConstructShortestPath(
        const std::map<std::string, std::string>& previous,
        const std::string& start,
        const std::string& end);

/**
 * @brief Implementacja algorytmu Dijkstry, która pozwala na wyznaczenie najkrótszej drogi między dwoma wierzchołkami w grafie skierowanym lub nieskierowanym. Algorytm działa poprzez przeglądanie wszystkich wierzchołków w grafie i aktualizację odległości od wierzchołka startowego.
 * @param graph Graf reprezentujący polączenie miast
 * @param start Nazwa początkowego wierzchołka w grafie
 * @param end Nazwa końcowego wierzchołka w grafie
 * @return Najkrótsza trasa i jej dystans, w postaci pary odległości i vectora z ułożonymi miastami.
 */
std::pair<double, std::vector<std::string>> dijkstra(
        const Graph& graph,
        const std::string& start,
        const std::string& end);

/**
 * @brief Funkcja odpowiedzialna za zapis wyznaczonej najkrótszej drogi z danego miasta. Funkcja tworzy plik, następnie dla każdego miasta poza miastem początkowym (start), wyznaczana jest najkrótsza droga za pomocą algorytmu Dijkstry, a następnie zapisywana jest do pliku w formie ciągu tekstowego.
 * @param fileName Nazwa pliku wyjśicowego
 * @param graph Graf reprezentujący polączenie miast
 * @param start Nazwa początkowego wierzchołka w grafie.
 */
void saveToFile(
        const std::string& fileName,
        const Graph& graph,
        const std::string& start);

/**
 * @brief Funkcja odpowiedzialna za przetwarzanie argumentów podanych przez użytkownika podczas uruchomienia programu. Funkcja iteruje przez argumenty i sprawdza, czy są one odpowiedniej postaci (np. "-i" i kolejny argument jest nazwą pliku wejściowego). Jeśli brakuje jakiegoś argumentu lub są one niepoprawne, funkcja wyświetla informację o poprawnym użyciu i zwraca pusty wektor.
 * @param argc Liczba argumentów przekazanych do programu
 * @param argv Tablica łańcuchów znaków, reprezentujących argumenty przekazane do programu
 * @return Vector z danymi wyprowadzonymi przez użytkownika
 */
std::vector<std::string> userData (
        int argc,
        char* argv[]);

/**
 * @brief Funkcja odpowiedzialna za sprawdzenie poprawności miasta podanego przez użytkownika. Funkcja zamienia pierwszą literę miasta na wielką literę, następnie sprawdza czy miasto znajduje się w grafie za pomocą metody count().
 * @param graph Graf reprezentujący polączenie miast
 * @param city Miasto wpisane przez uzytkownika
 * @return Prawde jeśli miasto zostało poprawnie wpisane
 */
bool correctCity(
        Graph& graph,
        std::string& city);