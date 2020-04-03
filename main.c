#include <stdio.h>

void congratulateUser(const char *userName)
{
        char buffer[80];
        // BAD: this could overflow the buffer if the UserName is long
        sprintf(buffer, "Congratulations, %s!", userName);
}

int main() {
   /* my first program in C */
   printf("Hello, World! \n");
   
   congratulateUser("A Very long name 123456789012345678911234567892123456789");
   
   return 0;
}
