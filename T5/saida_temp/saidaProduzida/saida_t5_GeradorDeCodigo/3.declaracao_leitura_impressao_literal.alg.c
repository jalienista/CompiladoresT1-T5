#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_LITERAL 80


int main(){
    char x[TAM_LITERAL];
    fgets(x, TAM_LITERAL, stdin);
    x[strcspn(x, "\n")] = '\0';
    printf("%s", x);
    return 0;
}
