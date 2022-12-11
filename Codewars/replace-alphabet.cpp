#include <string>
#include <sstream>

std::string alphabet_position(const std::string &text) {
    std::string result;
    
    for (char el: text){
        if ((int(toupper(el)) >= 65) and (int(toupper(el)) <= 90)){
            std::string letter_value = std::to_string(int(toupper(el)) - 64);
            result += letter_value + " ";
        } 
    }
    
    return result.substr (0, result.length()-1);
}