#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int testRand(){
	int a=(rand()%3);
	printf("a = %d \n" , a );
	return 1;
}

int main()
{
	srand(time(NULL));
	for(int i  = 0 ; i < 50 ; i++ ){
		testRand();
	}
	return 0;
}

