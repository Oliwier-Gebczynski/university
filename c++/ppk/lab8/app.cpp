#include <iostream>
#include <map>
#include <set>
#include <fstream>
#include <list>
#include <string>
#include <sstream>
#include <algorithm>

typedef std::map<std::string, std::set<std::pair<std::string, double>>> Graph;

Graph LoadFromFile(const std::string& fileName){
    Graph graph;
    std::ifstream in(fileName);
    if(in){
        std::string line;
        while (std::getline(in,line)){
            std::stringstream ss(line);
            std::string node1, node2;
            double length;

            if (!(ss >> node1)) continue;
            if (!(ss >> node2)) continue;
            if (!(ss >> length)) continue;
            graph[node1].insert({node2, length});
            graph[node2].insert({node1, length});

        }
        in.close();
    }
    return graph;
}

std::set<std::string> Bsf(const Graph& graph, const std::string& node){
    if (graph.find(node) == graph.end()) return {};


    std::set<std::string> result{ node };
    std::list<std::string> nodes { node };
    while (nodes.size()){
        for (const auto& el : graph.at(nodes.front())){
            if (result.count(el.first) == 0){
                result.insert(el.first);
                nodes.push_back(el.first);
            }
        }
        nodes.pop_front();
    }
    return result;
}

std::map<std::string, double> Dijkstra(const Graph& graph, std::string node){
    std::map<std::string, double> result;
    std::set<std::string> nodes;

    for (const auto& el : graph){
        result.[el.first] = std::numeric_limits<doube>::infinity();
    }

    if (graph.find(node) == graph.end()) return result;

    result[node] = 0.0;
    while (true){
        for (const auto& el : graph.at(node))
        {
            if (result[el.first] > result.[node] + el.second)
            {
                result[el.first] = result.[node] + el.second;
                nodes.insert(el.first);
            }  
        }

        if (nodes.empty()) break;
        std::min_element(nodes.begin(), nodes.end(), [&result](const auto& 1, const auto& r){ return result[1] < result[r];});
        nodes.erase(node);
    }  
}

int main() {    
    auto graph = LoadFromFile("graph.txt");
    auto n1 = Bsf(graph, "A");

    return 0;
}