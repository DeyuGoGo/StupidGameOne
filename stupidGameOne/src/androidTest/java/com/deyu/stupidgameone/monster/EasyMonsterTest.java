package com.deyu.stupidgameone.monster;

import android.test.AndroidTestCase;
import android.widget.ImageView;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by huangeyu on 15/3/24.
 */
public class EasyMonsterTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    @Test
    public void testGetFace(){
        MonsterListener listener = Mockito.mock(MonsterListener.class);
        final ImageView b = new ImageView(getContext());
        EasyMonster a = new EasyMonster("Deyu" ,listener , 5 , 1){
            @Override
            protected ImageView getFace() {
                return b;
            }
        };
        assertSame(b , a.getFace());
    }


}
