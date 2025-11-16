#include <cstring>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>

void malloc() {
    //int *p = (int*) malloc(sizeof(int));
    int *p = (int*) calloc(1, sizeof(int));

    if (p == NULL) {
        printf("Failed to allocate memory\n");
    }
    *p = 5;
    printf("%d", *p);
    free(p);
    p = NULL;
    free(p);
}

void newMemory() {
    int *p = new int(5);
    *p = 6;
    std::cout << *p << std::endl;
    delete p;
    p = nullptr;
}

void newArray() {
    int *p = new int[5]{1,2,3,4,5};

    // for (int i = 0; i < 5; i++) {
    //         p[i] = i;
    // }
    delete []p;
}

void strings() {
    char *p = new char[4];
    strcpy(p, "C++");
    std::cout << p << std::endl;
    delete []p;
}

void twoD() {
    int *p1 = new int[3];
    int *p2 = new int[3];

    int **pData = new int *[2];
    pData[0] = p1;
    pData[1] = p2;

    pData[0][1] = 2;

    delete []p1;
    delete []p2;

    delete []pData;
}


int main() {
    strings();

    return 0;
}

