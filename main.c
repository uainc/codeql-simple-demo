#include <stdio.h>

void congratulateUser(const char *userName)
{
        char buffer[80];
        /* BAD: this could overflow the buffer if the UserName is long */
        sprintf(buffer, "Congratulations, %s!", userName);
        
        printf("%s\n", buffer);
}

int main() {
   char aName[256] = {0};
        
   /* my first program in C */
   printf("What is your name?\n");
   
   scanf("%s", aName);
   
   congratulateUser(aName);
   
   return 0;
}
