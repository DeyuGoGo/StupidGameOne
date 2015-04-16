package com.deyu.stupidgameone.brave;

/**
 * Created by huangeyu on 15/3/23.
 */
public class Baby extends Brave implements Novice {

    public Baby() {
        super("I just a baby");
    }

    public Baby(String name) {
        super(name);
    }

    @Override
    public String WhatIsMyName() {
        return Name;
    }
}
