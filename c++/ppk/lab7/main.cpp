#include <iostream>
#include <string>
#include <map>
#include <fstream>
#include <iomanip>
#include <vector>
#include <algorithm>


bool isChar( char c ){
    c |= 0x20;
    return c >= 'a' && c <= 'z';
}

std::map<std::string, int> Histogram(const std::string& fileName){
    std::map<std::string, int> result;
    std::ifstream in(fileName);
    if (in)
    {   
        std::string word;
        while (in >> word)
        {
            auto it = std::remove_if(word.begin(), word.end(), [](const auto c) { return !isChar(c); });
            word.erase(it, word.end());
            if (word.length() == 0) continue;
            ++result[word];    
        }
        
        in.close();
    }
    return result;
}

int main(){
    auto h = Histogram("plik.txt");

    for (const auto& el: h){
        std::cout << std::setw(6) << el.second << "  |  " << el.first << std::endl;
    }
}

