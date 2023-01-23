#include "main.h"

Graph LoadFromFile(const std::string& fileName) {
    Graph graph;
    std::fstream in(fileName);

    if (in) {
        std::string line;
        while (std::getline(in, line)) {
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

std::vector<std::string> ConstructShortestPath(const std::map<std::string, std::string>& previous, const std::string& start, const std::string& end){
    std::vector<std::string> path;
    for (std::string v = end; v != start; v = previous.at(v)) {
        path.push_back(v);
    }
    path.push_back(start);
    std::reverse(path.begin(), path.end());
    return path;
}

std::pair<double, std::vector<std::string>> dijkstra(const Graph& graph, const std::string& start, const std::string& end){
    std::map<std::string, double> result;
    std::map<std::string, std::string> previous;
    std::priority_queue<std::pair<double, std::string>, std::vector<std::pair<double, std::string>>, std::greater<>> pq;

    for (const auto& el : graph) {
        result[el.first] = std::numeric_limits<double>::infinity();
    }

    pq.push({0, start});

    while (!pq.empty()) {
        double distance = pq.top().first;
        std::string vertex = pq.top().second;
        pq.pop();

        if (result[vertex] != result[vertex]) {
            continue;
        }

        if (vertex == end) {
            return {distance, ConstructShortestPath(previous, start, end)};
        }

        for (const auto& el : graph.at(vertex)) {
            if (result[el.first] > distance + el.second) {
                result[el.first] = distance + el.second;
                previous[el.first] = vertex;
                pq.emplace(distance + el.second, el.first);
            }
        }
    }

    return {result[end], ConstructShortestPath(previous, start, end)};
}

void saveToFile(const std::string& fileName, const Graph& graph, const std::string& start){
    std::ofstream out(fileName);

    if (out){
        for (const auto& element : graph){
            if (element.first != start){
                std::string string_path;
                auto path = dijkstra(graph, start, element.first);
                size_t lenght = path.second.size();

                for (const auto& item : path.second){
                    if(lenght>1){
                        lenght--;
                        string_path += item + " -> ";
                    }else{
                        string_path += item + ": ";
                    }
                }
                string_path += " " + std::to_string(path.first);
                out << string_path << std::endl;
            }
        }
        out.close();
    }

    std::cout << "Done!" << std::endl;
}

std::vector<std::string> userData (int argc, char* argv[]){
    std::string input_file;
    std::string output_file;
    std::string start_city;

    for (int i = 1; i < argc; i++) {
        std::string element = argv[i];
        if (element == "-i" && i + 1 < argc){
            input_file = argv[i + 1];
        }else if (element == "-o" && i + 1 < argc){
            output_file = argv[i + 1];
        }else if (element == "-s" && i + 1 < argc){
            start_city = argv[i + 1];
        }
    }

    if (input_file.empty() || output_file.empty() || start_city.empty()) {
        std::cout << "Usage: " << argv[0] << " -i input_file -o output_file -s start_city" << std::endl;

        std::vector<std::string> result = {};
        return result;
    }

    std::vector<std::string> result = {input_file, output_file, start_city};
    return result;
}

bool correctCity(Graph& graph, std::string& city){
    std::transform(city.begin(), city.begin() + 1, city.begin(), ::toupper);

    if (graph.count(city)){
        return true;
    }else{
        std::cout << "Incorrect city" << std::endl;
        return false;
    }
}
