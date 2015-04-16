package com.deyu.stupidgameone.activity;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.deyu.stupidgameone.R;
import com.deyu.stupidgameone.fragment.ArenaFragment;

import org.junit.Test;

/**
 * Created by huangeyu on 15/3/24.
 */
public class ArenaActivityTest extends ActivityInstrumentationTestCase2<ArenaActivity> {

    ArenaActivity mArenaActivity;

    public ArenaActivityTest() {
        super(ArenaActivity.class);
    }

    public ArenaActivityTest(Class<ArenaActivity> activityClass) {
        super(ArenaActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        mArenaActivity = getActivity();
        super.setUp();
    }

    @Test
    public void testCheckFirstFragment() throws Exception {
        Fragment f = mArenaActivity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertNotNull(f);
        assertTrue(f instanceof ArenaFragment);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
