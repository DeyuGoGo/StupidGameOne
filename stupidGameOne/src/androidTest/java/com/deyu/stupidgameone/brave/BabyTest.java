package com.deyu.stupidgameone.brave;

import android.test.AndroidTestCase;

import org.junit.Test;

/**
 * Created by huangeyu on 15/3/23.
 */
public class BabyTest extends AndroidTestCase{

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testWhatIsMyName()throws Exception{
        String name = "Deyu";
        Novice a = new Baby(name);
        assertNotNull(a.WhatIsMyName());
        assertEquals(a.WhatIsMyName() , name);
    }

}
