package com.deyu.stupidgameone.activity;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

/**
 * Created by huangeyu on 15/3/24.
 */
public class ArenaActivityTest extends ActivityInstrumentationTestCase2<ArenaActivity>{

    public ArenaActivityTest(){
        super(ArenaActivity.class);
    }

    public ArenaActivityTest(Class<ArenaActivity> activityClass) {
        super(ArenaActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        getActivity();
        super.setUp();
    }

    @Test
    public void testWhatIsMyName()throws Exception{
//        launchActivity("com.deyu.stupidgameone",ArenaActivity.class , new Bundle());
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
