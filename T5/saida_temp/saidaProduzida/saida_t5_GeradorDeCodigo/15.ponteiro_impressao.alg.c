#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_LITERAL 80


int main(){
    int x;
    int* endx;
    x = 0;
    printf("%d", x);
    printf("%s", " e ");
    endx = &x;
    *endx = 1;
    printf("%d", x);
    return 0;
}
