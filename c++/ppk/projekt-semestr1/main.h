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
    *@brief Defines a type alias for a graph
    *A graph is represented as a dictionary where each key is a node represented by a string and its value is a set of edges. Each edge is represented by a pair of strings and a double. The first string is the name of the neighboring node, and the second string is the weight of the edge.
*/
typedef std::map<std::string, std::set<std::pair<std::string, double>>> Graph;

/**
 * @brief Loads a graph from a file
 * @param fileName
 * @return The loaded graph
 */
Graph LoadFromFile(const std::string& fileName);

/**
 * @brief Creates path from previous map
 * @param previous
 * @param start
 * @param end
 * @return The vector with ordered cities
 */
std::vector<std::string> ConstructShortestPath(
        const std::map<std::string, std::string>& previous,
        const std::string& start,
        const std::string& end);

/**
 * @brief Dijkstra sort
 * @param graph
 * @param start
 * @param end
 * @return Shortest path and distance
 */
std::pair<double, std::vector<std::string>> dijkstra(
        const Graph& graph,
        const std::string& start,
        const std::string& end);

/**
 * @brief Save all shortest path to file
 * @param fileName
 * @param graph
 * @param start
 */
void saveToFile(
        const std::string& fileName,
        const Graph& graph,
        const std::string& start);

/**
 * @brief Gets data from user and checks errors
 * @param argc
 * @param argv
 * @return The vector with all user data
 */
std::vector<std::string> userData (
        int argc,
        char* argv[]);

/**
 * @brief Checks city name
 * @param graph
 * @param city
 * @return Return true if city name is correct
 */
bool correctCity(
        Graph& graph,
        std::string& city);