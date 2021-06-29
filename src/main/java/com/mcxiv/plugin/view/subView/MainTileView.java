package com.mcxiv.plugin.view.subView;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.util.GdxUtil;

public class MainTileView extends VisTable {
    public MainTileView(TextureRegion texture) {
        Drawable drw = new TextureRegionDrawable(texture);
        add(new VisImageButton(drw))
                .width(texture.getRegionWidth() * 80f / texture.getRegionHeight())
                .height(80);


    }
}
