package com.Allicnce.taobaoAlliance.common.richpath.model;

import android.content.Context;
import android.content.res.XmlResourceParser;
import com.Allicnce.taobaoAlliance.common.richpath.RichPath;
import com.Allicnce.taobaoAlliance.common.richpath.util.XmlParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarek on 6/30/17.
 */

public class Vector {

    public final static String TAG_NAME = "vector";

    private String name;
    private int tint;
    private float height;
    private float width;
    private float alpha;

    private boolean autoMirrored;

    //TODO private PorterDuff.Mode tintMode = PorterDuff.Mode.SRC_IN;

    private float viewportWidth;
    private float viewportHeight;

    public List<RichPath> paths = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getViewportWidth() {
        return viewportWidth;
    }

    public float getViewportHeight() {
        return viewportHeight;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void inflate(XmlResourceParser xpp, Context context) {

        name = XmlParser.getAttributeString(context, xpp, "name", name);

        tint = XmlParser.getAttributeColor(context, xpp, "tint", tint);

        width = XmlParser.getAttributeDimen(context, xpp, "width", width);

        height = XmlParser.getAttributeDimen(context, xpp, "height", height);

        alpha = XmlParser.getAttributeFloat(xpp, "alpha", alpha);

        autoMirrored = XmlParser.getAttributeBoolean(xpp, "autoMirrored", autoMirrored);

        viewportWidth = XmlParser.getAttributeFloat(xpp, "viewportWidth", viewportWidth);

        viewportHeight = XmlParser.getAttributeFloat(xpp, "viewportHeight", viewportHeight);

        //TODO tintMode = XmlParser.getAttributeFloat(xpp, "tintMode", tintMode);

    }
}
