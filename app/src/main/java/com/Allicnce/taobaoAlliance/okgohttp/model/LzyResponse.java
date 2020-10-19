package com.Allicnce.taobaoAlliance.okgohttp.model;

import java.io.Serializable;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:00
 * @Email 1016224774@qq.com
 * @Description
 */
public class LzyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int code;
    public String msg;
    public T data;
}