#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_LITERAL 80


int main(){
    struct{ char nome[TAM_LITERAL]; int idade; } reg;
    strcpy(reg.nome, "Maria");
    reg.idade = 24;
    printf("%s", reg.nome);
    printf("%s", " tem ");
    printf("%d", reg.idade);
    printf("%s", " anos");
    return 0;
}
