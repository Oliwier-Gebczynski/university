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


typedef std::map<std::string, std::set<std::pair<std::string, double>>> Graph;


Graph LoadFromFile(const std::string& fileName);


std::vector<std::string> construct_shortest_path(
        const std::map<std::string, std::string>& previous,
        const std::string& start,
        const std::string& end);


std::pair<double, std::vector<std::string>> dijkstra(
        const Graph& graph,
        const std::string& start,
        const std::string& end);

void saveToFile(
        const std::string& fileName,
        const Graph& graph,
        const std::string& start);

std::vector<std::string> userData (
        int argc,
        char* argv[]);

bool correctCity(
        Graph& graph,
        std::string& city);