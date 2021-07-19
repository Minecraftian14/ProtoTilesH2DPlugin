package com.mcxiv.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public abstract class GdxTest extends InputAdapter implements ApplicationListener {
    public void create () {
        new Vector2().nor();
    }

    public void resume () {
    }

    public void render () {
    }

    public void resize (int width, int height) {
    }

    public void pause () {
    }

    public void dispose () {
    }
}
