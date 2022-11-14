#include <iostream>
#include <vector>
#include <string>

int getSize(const std::string &text = "size = "){
    int n;

    do {
        std::cout << text;
        std::cin >> n;
    }while( n < 1);
    return n;
}

// void ShowVector(const std::vector<double> tab){
//     std::cout << "[ ";
    
//     for (const auto el : tab){
//         std::cout << el << " ";
//     }

//     std::cout<< "]";
// }

// void ReadVector( std::vector<double> &tab){
//     int i = 0;
//     for( auto &el : tab ){
//         std::cout << "tab[" << ++i << "] = ";
//         std::cin >> el;
//     }
// }

void ShowMatrix(const std::vector<std::vector<double>> &m){
    for (const auto & row: m){
        for (const auto el: row){
            std::cout << el << " ";
        }
        std::cout << std::endl;
    }
}

void ReadMatrix( std::vector<std::vector<double>> &m){
    int i = 0;
    int j = 0;
    for (auto &el : m){
        for (auto &item: el){
            std::cout << "m[ " << i << "[ " << j << " ]] = ";
            std::cin >> item;
            ++j;
        }
        ++i;
    }
}

int main(){
    // int size = getSize();
    // std::vector<double> v(size);
    // ReadVector(v);
    // ShowVector(v);

    const int rows = getSize("Rows: ");
    const int cols = getSize("Cols: ");

    std::cout << rows << cols;
    std::vector<std::vector<double>> matrix(rows, std::vector<double>(cols));

    ReadMatrix(matrix);
    ShowMatrix(matrix);
}