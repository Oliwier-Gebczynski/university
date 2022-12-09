#include <string>

std::string sliceString (std::string str )
{
    std::string result;
    result = str.substr(1, str.length()-2 );
    
    return result;
}