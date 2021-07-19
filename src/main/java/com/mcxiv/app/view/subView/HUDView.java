package com.mcxiv.app.view.subView;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.generators.Grass;
import com.mcxiv.app.util.Color;
import com.mcxiv.app.util.GdxUtil;

public class HUDView extends VisTable {

    private final VisImageButton fie_tileView;

    private final ObjectMap<String, Texture> tiles = new ObjectMap<>();
    private final VisSelectBox<String> fie_tileSelector;

    private Color[][] selected;

    public HUDView() {

        defaults().space(5);

        Pixmap map = GdxUtil.getPixmap(selected = new Grass().algo1(16));
        Pixmap fin = new Pixmap(120, 120, map.getFormat());
        fin.drawPixmap(map, 0, 0, map.getWidth(), map.getHeight(), 0, 0, fin.getWidth(), fin.getHeight());
        Texture texture = new Texture(fin);
        fie_tileView = new VisImageButton(new TextureRegionDrawable(texture));
        tiles.put("Default", texture);
        add(fie_tileView).row();
        fin.dispose();
        map.dispose();

        fie_tileSelector = new VisSelectBox<>();
        fie_tileSelector.setItems(tiles.keys().toArray());
        add(fie_tileSelector);

    }

    public Color[][] getSelected() {
        return selected;
    }
}
