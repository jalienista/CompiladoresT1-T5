#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_LITERAL 80


int main(){
    int i;
    i = 1;
    while (i <= 5){
        printf("%d", i);
        printf("%s", "\n");
        i = i + 1;
    }
    return 0;
}
