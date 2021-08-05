package com.mcxiv.app.view.subView;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.mcxiv.app.view.ProtoTilesView;

public class MainTileView extends VisTable {

    public MainTileView(TextureRegion texture) {


        Drawable drw = new TextureRegionDrawable(texture);

        add(new VisImageButton(drw))
                .width(texture.getRegionWidth() * 80f / texture.getRegionHeight())
                .height(80);


    }
}
