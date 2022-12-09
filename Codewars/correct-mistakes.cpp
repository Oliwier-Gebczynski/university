#include <string>

std::string correct(std::string str){
    for (int i = 0; str.length() > i; i++){
        if (str[i] == '5') str.replace(i, 1, "S");
        else if (str[i] == '0') str.replace(i, 1, "O");
        else if (str[i] == '1') str.replace(i, 1, "I");
    }

    return str;
}