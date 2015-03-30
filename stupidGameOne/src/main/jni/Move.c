#include <com_deyu_stupidgameone_motion_Move.h>
#include <Calc.h>


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

