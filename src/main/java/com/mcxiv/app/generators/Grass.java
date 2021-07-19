package com.mcxiv.app.generators;

import com.badlogic.gdx.math.MathUtils;
import com.mcxiv.app.util.ArrUtil;
import com.mcxiv.app.util.Color;

public class Grass {

    public Color from(java.awt.Color col) {
        return new Color(col.getRed() / 255f, col.getGreen() / 255f, col.getBlue() / 255f, col.getAlpha() / 255f);
    }

    java.awt.Color green = new java.awt.Color(58, 182, 15);
    java.awt.Color brown = new java.awt.Color(99, 47, 6);

    public Color[][] algo1(int size) {
        Color[][] pixels = new Color[size][size];

        for (int i = 0; i < size; i++) {
            int h = (int) (0.1 * MathUtils.random() * size + 0.25 * size);
            for (int j = 0; j < size; j++) {

                Color cl;
                if (j < h) cl = ArrUtil.newColor(from(Math.random() > .5 ? green.brighter() : green.darker()));
                else /* */ cl = ArrUtil.newColor(from(Math.random() > .5 ? brown.brighter() : brown.darker()));

                pixels[i][j] = cl;

            }
        }
        return pixels;
    }

}
