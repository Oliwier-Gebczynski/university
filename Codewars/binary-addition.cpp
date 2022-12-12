#include <cstdint>
#include <string>
#include <bitset>

std::string add_binary(uint64_t a, uint64_t b) {
    std::string result = std::bitset<1000>(a + b).to_string();
    
    if ( (result == "0") or ((a + b) == 0) ){
      return "0";
    }else{
      return result.erase(0, result.find_first_not_of('0'));
    }
}