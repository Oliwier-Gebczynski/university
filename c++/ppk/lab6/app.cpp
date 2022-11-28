#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>

struct PersonalData
{
    std::string firstName;
    std::string lastName;
    int age;
    bool student;
};

void show(const PersonalData& pd){ 
    std::cout << "First name: " << pd.firstName << std::endl;
    std::cout << "Last name: " << pd.lastName << std::endl;
    std::cout << "Age: " << pd.age << std::endl;

    std::string v = (pd.student == true) ? "yes" : "no";
    std::cout << "Student?: " << v << std::endl;

}

void show2(const std::vector<PersonalData>& pd){
    for (const auto& el : pd){
        show(el);
    }
}

void createPerson(PersonalData& pd){
    std::cout << "----------------- Add person ---------------------" << std::endl;

    std::cout << "Your first name: ";
    std::cin >> pd.firstName;

    std::cout << "Your second name: ";
    std::cin >> pd.lastName; 

    std::cout << "Your age: ";
    std::cin >> pd.age; 

    std::cout << "Student?: ";
    std::cin >> pd.student; 

    std::cout << "---------------------------------------------------" << std::endl;

}

void createPersons(std::vector<PersonalData>& pd){
    for (auto& el : pd){
        createPerson(el);
    }
}

void saveFile(std::vector<PersonalData>& pd){
    std::string fileName;
    std::cout << "Type file name ... (.txt at the end)";
    std::cin >> fileName;

    std::ofstream out(fileName);

    if(out){
        for (auto& el : pd){
            out << el.firstName << ";" << el.lastName << ";" << el.age << ";" << el.student << std::endl;
        }
        out.close();
    }

}

std::vector<PersonalData> loadFile(){
    std::vector<PersonalData> result;

    std::string fileName;
    std::cout << "Choose file ... (.txt at the end)";
    std::cin >> fileName;

    std::fstream in(fileName);
    
    if (in){
        std::string line;
        while (std::getline(in, line)){

            if (line.length() == 0) break;
            
            std::stringstream ss(line);
            std::string firstName, lastName, age, student;
            bool studentBool;

            std::getline(ss, firstName, ';');
            std::getline(ss, lastName, ';');
            std::getline(ss, age, ';');
            std::getline(ss, student, ';');

            if (student == "1"){
                studentBool = true;
            }else{
                studentBool = false;
            }

            result.push_back({firstName, lastName, stoi(age), studentBool});
        }

        in.close();
    }

    return result;
}


int main(){
    PersonalData pd;

    // std::vector<PersonalData> persons(2);

    // createPersons(persons);
    // show2(persons);

    // saveFile(persons);
    auto z = loadFile();

    show2(z);
}