package com.deyu.stupidgameone.motion;

import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.Test;

/**
 * Created by huangeyu on 15/3/30.
 */
public class MoveTest extends AndroidTestCase {

    private Move c;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        c = new Move();
    }

    @Test
    public void testGetWhereToGo() {
        c.init();
        for (int i = 0; i < 50; i++) {
            int WhereToGO = c.getWhereToGo(1024, 768, 1025, 300, 50, 50, 3);
            Log.d("DEYU", "WhereToGo : " + WhereToGO);
        }
    }
}
