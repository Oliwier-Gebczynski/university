#include <string>

std::string no_space(const std::string& x)
{
    std::string result;
    
    for (size_t i = 0; i < x.length(); i++){
        if (x[i] != ' ') result.push_back(x[i]); 
    }
    
   return result;
}