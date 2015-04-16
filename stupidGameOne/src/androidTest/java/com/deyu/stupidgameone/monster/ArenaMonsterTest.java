package com.deyu.stupidgameone.monster;

import android.test.AndroidTestCase;

import com.deyu.stupidgameone.R;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by huangeyu on 15/3/24.
 */
public class ArenaMonsterTest extends AndroidTestCase {
    ArenaMonster a;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        a = new ArenaMonster("Deyu", 5, 1, R.drawable.ic);
    }

    @Test
    public void testisAlive() {
        a.HP = 3;
        assertTrue(a.isAlive());
        a.HP = 0;
        assertFalse(a.isAlive());
        a.HP = -4;
        assertFalse(a.isAlive());
    }

    @Test
    public void testSetListener() {
        MonsterListener listener = Mockito.mock(MonsterListener.class);
        a.setListener(listener);
        assertSame(listener, a.Listener);
    }

    @Test(expected = NullPointerException.class)
    public void testGetSayNull() {
        a.getSay();
    }
}
