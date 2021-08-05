package com.mcxiv.app.ui;

import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.VisSelectBox;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class StaticInstanceSelector<T> extends VisSelectBox<String> {

    private final ObjectMap<String, T> referenceMap = new ObjectMap<>();

    public StaticInstanceSelector(Class<T> clazz) {

        for (Field field : clazz.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            if (!clazz.isAssignableFrom(field.getType())) continue;

            try {
                referenceMap.put(field.getName(), (T) field.get(null));
            } catch (Exception ignored) {
            }
        }

        setItems(referenceMap.keys().toArray());

    }

    public T getSelectedT() {
        return referenceMap.get(getSelected());
    }

}
