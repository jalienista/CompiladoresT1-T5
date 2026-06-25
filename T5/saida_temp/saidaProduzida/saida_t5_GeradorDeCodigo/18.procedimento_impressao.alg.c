#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_LITERAL 80

void proc_imprime(char* mensagem);

void proc_imprime(char* mensagem){
    printf("%s", mensagem);
    printf("%s", "\n");
}

int main(){
    proc_imprime("teste");
    return 0;
}
