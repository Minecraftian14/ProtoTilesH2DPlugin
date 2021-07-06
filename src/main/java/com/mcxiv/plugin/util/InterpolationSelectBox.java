package com.mcxiv.plugin.util;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.mcxiv.app.util.Blending;
import com.mcxiv.app.util.functions.FloatFunction;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class InterpolationSelectBox extends VisSelectBox<String> implements FloatFunction {

    private static final ObjectMap<String, FloatFunction> interpolatorsMap = new ObjectMap<>();

    static {
        for (Field field : Interpolation.class.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            try {
                Object object = field.get(null);
                if(!(object instanceof Interpolation))
                    continue;
                Interpolation interpolation = (Interpolation) object;
                interpolatorsMap.put(field.getName(), interpolation::apply);
            } catch (IllegalAccessException ignored) {
            }
        }
    }

    public InterpolationSelectBox() {
        setItems(interpolatorsMap.keys().toArray());
    }

    @Override
    public float on(float a) {
        return interpolatorsMap.get(getSelected()).on(a);
    }
}
