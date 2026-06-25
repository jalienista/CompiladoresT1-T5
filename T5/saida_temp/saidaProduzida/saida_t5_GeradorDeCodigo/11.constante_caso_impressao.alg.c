#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_LITERAL 80

const int teste = 8;

int main(){
    switch (teste){
        case 0:
            printf("%s", "ERRO");
            break;
        case 1:
            printf("%s", "ERRO");
            break;
        case 2:
            printf("%s", "ERRO");
            break;
        case 3:
            printf("%s", "ERRO");
            break;
        case 4:
            printf("%s", "ERRO");
            break;
        case 5:
            printf("%s", "ERRO");
            break;
        case 6:
            printf("%s", "ERRO");
            break;
        case 7:
            printf("%s", "ERRO");
            break;
        case 8:
            printf("%s", "OK");
            break;
        default:
            printf("%s", "ERRO");
    }
    return 0;
}
