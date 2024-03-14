import <iostream>;
import <vector>;
import FizzBuzz;

int main() {
    std::vector<int> v;
    int number = 100;

    for (int i = 0; i < number; i++) {
        v.push_back(i);
    }
    FizzBuzz(v);
}
