#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <Calc.h>

int WHERE_TO_GO(int w, int h, int x, int y, int imgw, int imgh){
	srand(time(NULL));
	int a=(rand()%5);
	if(x > (w - imgw)){
		return shouldgoLeft(a);
	}
	if(x < 0){
		return shouldgoRight(a);
	}
	if(y > (h - imgh)){
		return shouldgoUp(a);
	}
	if(y  < 0){
		return shouldgoDown(a);
	}
	return 0;
}
int shouldgoLeft(int randomWhere){
	if(randomWhere==0){
		randomWhere = 1;
	}else{
		randomWhere +=4;
	}
	return randomWhere;
}
int shouldgoRight(int randomWhere){
	randomWhere +=1;
	return randomWhere;
}
int shouldgoDown(int randomWhere){
	randomWhere +=3;
	return randomWhere;
}
int shouldgoUp(int randomWhere){
	randomWhere +=1;
	if(randomWhere > 3){
		randomWhere+=3;
	}
	return randomWhere; 
}