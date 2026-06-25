#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_LITERAL 80

int dobro(int x);

int dobro(int x){
    return 2 * x;
}

int main(){
    printf("%d", dobro(4));
    return 0;
}
