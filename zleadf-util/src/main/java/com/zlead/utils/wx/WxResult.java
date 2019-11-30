package com.zlead.utils.wx;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by xujiajun on 15/2/14.
 */
public class WxResult implements Serializable{

    private int state ;
    private Map<String,String> cont;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Map<String, String> getCont() {
        return cont;
    }

    public void setCont(Map<String, String> cont) {
        this.cont = cont;
    }
}

