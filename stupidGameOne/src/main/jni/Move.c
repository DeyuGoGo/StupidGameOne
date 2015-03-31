#include <com_deyu_stupidgameone_motion_Move.h>
#include <stdio.h>
#include <stdlib.h>
#include <Calc.h>
#include <time.h>


static int a = 0;

/*
 * Class:     com_deyu_stupidgameone_motion_Move
 * Method:    init
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_deyu_stupidgameone_motion_Move_init
  (JNIEnv *env, jobject obj){
  			srand(time(NULL));
  };

/*
/*
 * Class:     com_deyu_stupidgameone_motion_Move
 * Method:    getWhereToGo
 * Signature: (IIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_deyu_stupidgameone_motion_Move_getWhereToGo
  (JNIEnv *env, jobject obj, jint w, jint h, jint x, jint y, jint imgw, jint imgh, jint whereToGo){
  	if(WHERE_TO_GO(w,h,x,y,imgw,imgh)!=0){
  	return WHERE_TO_GO(w,h,x,y,imgw,imgh) ;
  }
  return whereToGo;
  };

