package com.mcxiv.app.view.subView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.mcxiv.app.util.Color;
import com.mcxiv.app.util.GdxUtil;
import com.mcxiv.app.view.TilesetEditor;

public class HUDView extends VisTable {

    private final TilesetEditor tilesetEditor;
    private final VisImageButton fie_tileView;

    private final ObjectMap<String, Texture> tiles = new ObjectMap<>();
    private final VisSelectBox<String> fie_tileSelector;

    private Color[][] selected;

    public HUDView(TilesetEditor app) {
        this.tilesetEditor = app;

        defaults().space(5);

        fie_tileView = new VisImageButton(new TextureRegionDrawable());
        fie_tileSelector = new VisSelectBox<>();

        add(fie_tileView).row();
        add(fie_tileSelector);

        setImage("Default", new Texture(Gdx.files.internal("assets/grass.png")));

        FileChooser file = new FileChooser("Open a file", FileChooser.Mode.OPEN);
        file.setListener(new FileChooserAdapter() {
            @Override
            public void selected(Array<FileHandle> file) {
                setImage(file.first().name(), new Texture(file.first()));
                tilesetEditor.view_TileSet.update();
            }
        });

        fie_tileView.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                file.toFront();
                app.addActor(file.fadeIn(1));
            }
        });

        fie_tileSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Texture texture = tiles.get(fie_tileSelector.getSelected());
                selected = GdxUtil.getPixels(texture);
                fie_tileView.getStyle().imageUp = new TextureRegionDrawable(GdxUtil.resizeTo(GdxUtil.getPixmap(texture), 120));
                tilesetEditor.view_TileSet.update();
            }
        });

    }

    public void setImage(String name, Texture image) {

        Pixmap map = GdxUtil.getPixmap(image);
        selected = GdxUtil.getPixels(map);
        tiles.put(name, image);
        fie_tileSelector.setItems(tiles.keys().toArray());
        fie_tileSelector.setSelected(name);

        fie_tileView.getStyle().imageUp = new TextureRegionDrawable(GdxUtil.resizeTo(map, 120));
        map.dispose();

    }

    public Color[][] getSelected() {
        return selected;
    }
}
