#include <iostream>
#include <vector>
#include <string>

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
    std::cout << "Student?: " << pd.student << std::endl;

}

void show2(const std::vector<PersonalData>& pd){
    for (const auto& el : pd){
        show(el);
    }
}
/*
wskaznikowa wersja 

void show(const PersonalData * const pd){ 
    std::cout << "First name: " << pd -> firstName << std::endl;
    std::cout << "Last name: " << pd -> lastName << std::endl;
}
*/

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

/*
wersja wskaznikowa

void createPerson(PersonalData * const pd){
    std::cout << "----------------- Add person ---------------------" << std::endl;

    std::cout << "Your first name: ";
    std::cin >> pd -> firstName;

    std::cout << "Your second name: ";
    std::cin >> pd -> lastName; 

    std::cout << "---------------------------------------------------" << std::endl;
}

*/

int main(){
    PersonalData pd;
    //createPerson(pd);
    //show(pd);

    std::vector<PersonalData> persons(2);

    createPersons(persons);
    show2(persons);

}