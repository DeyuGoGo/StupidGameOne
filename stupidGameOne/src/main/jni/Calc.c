#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <Calc.h>

int WHERE_TO_GO(int w, int h, int x, int y, int imgw, int imgh){
	int a=(rand()%3);
	printf("a = : %d \n", a);
	if(x > (w - imgw) && y > (h - imgh)){
		return shouldgoLeftUp(a);
	}
	if(x > (w - imgw) && y  < 0){
		return shouldgoLeftDown(a);
	}
	if(x < 0 && y > (h - imgh)){
		return shouldgoRightUp(a);
	}
	if(x < 0 && y  < 0){
		return shouldgoRightDown(a);
	}
	int b=(rand()%5);
	if(x > (w - imgw)){
		return shouldgoLeft(b);
	}
	if(x < 0){
		return shouldgoRight(b);
	}
	if(y > (h - imgh)){
		return shouldgoUp(b);
	}
	if(y  < 0){
		return shouldgoDown(b);
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
int shouldgoLeftDown(int randomWhere){
	randomWhere+=5;
	return randomWhere;
}
int shouldgoLeftUp(int randomWhere){
	if(randomWhere==0){
		randomWhere+=1;
	}else{
		randomWhere+=6;
	}
	return randomWhere;
}
int shouldgoRightUp(int randomWhere){
	randomWhere += 1;
	return randomWhere;
}
int shouldgoRightDown(int randomWhere){
	randomWhere +=3;
	return randomWhere;
}
