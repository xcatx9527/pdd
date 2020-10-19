package com.Allicnce.taobaoAlliance.okgohttp.model;

import java.io.Serializable;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:00
 * @Email 1016224774@qq.com
 * @Description
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public LzyResponse toLzyResponse() {
        LzyResponse lzyResponse = new LzyResponse();
        lzyResponse.code = code;
        lzyResponse.msg = msg;
        return lzyResponse;
    }
}