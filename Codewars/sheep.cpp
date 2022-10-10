#include <vector>
#include <
using namespace std; 

int count_sheep(vector<bool> arr) 
{
    int counter{};
    int size = sizeof( arr ) / sizeof( arr[0] );
    cout << size;

    for (int i = 0; i < size ; i++){
        if (arr[i] == true){
            counter++;
        }
    };

    return counter;
}