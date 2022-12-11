# include <string>
# include <vector>
# include <algorithm>

std::string disemvowel(const std::string& str) {
    std::vector<char> vovels{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    std::string result;

    for (char el: str){
        if ((std::find (vovels.begin(), vovels.end(), el) == vovels.end())) result += el;
    }

    return result;
}