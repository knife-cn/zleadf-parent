/**
 * @program: zleadf-parent
 * @description:msg
 * @author: ytchen
 * @create: 2019-02-28 16:11
 **/
package com.zlead.entity;


import java.io.Serializable;


public class MsgEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    private String phone;
    private String msg;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
