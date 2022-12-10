#include <vector>

using namespace std; 

int findSmallest(vector <int> list)
{
    int smallest;
    for ( int el: list ){
        if((smallest > el) or (smallest == NULL)) smallest = el;
    }
    
    return smallest;
}