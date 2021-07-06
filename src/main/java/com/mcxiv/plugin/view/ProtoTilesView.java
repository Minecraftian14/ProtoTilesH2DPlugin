package com.mcxiv.plugin.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.mcxiv.app.PackedTiles;
import com.mcxiv.app.util.AppUtil;
import com.mcxiv.plugin.ProtoTilesPlugin;
import com.mcxiv.plugin.util.GdxUtil;
import com.mcxiv.plugin.view.subView.DissectorView;
import com.mcxiv.plugin.view.subView.MainTileView;
import com.mcxiv.plugin.view.subView.SettingsPaneView;
import com.mcxiv.plugin.view.subView.TileSetView;
import games.rednblack.h2d.common.MsgAPI;
import games.rednblack.h2d.common.UIDraggablePanel;

public class ProtoTilesView extends VisTable {

    private final ProtoTilesPlugin plugin;
    private final SettingsPaneView settings;

    public ProtoTilesView(ProtoTilesPlugin plugin, TextureRegion texture, String name) {
        this.plugin = plugin;
        pad(10);

        settings = new SettingsPaneView(this);

        setViewFor(texture, name);
    }

    private TextureRegion region;
    private String regionName;

    public void setViewFor(TextureRegion _region, String _regionName) {
        if (region != _region || !region.equals(_region)) {
            region = _region;
            regionName = _regionName;
            settings.updateTransparencySlider(region.getRegionWidth());
        }
        clear();

        if (region == null) region = GdxUtil.getBlackTextureRegion(80);

        PackedTiles lastCache = settings.getPackedTiles(region);

        add(settings).colspan(2).space(20).row();
        add(new MainTileView(region)).space(20);
//        add(new TileSetView(lastCache)).space(20).row();
        add(
                new VisScrollPane(
                        new TileSetView(lastCache)
//                        new DissectorView(GdxUtil.getPixels(region), settings)
                )
        ).space(20).row();
        add(getAddButton(regionName, lastCache)).space(20).colspan(2);
    }

    public void setViewAgain() {
        setViewFor(region, regionName);
    }

    private VisTextButton getAddButton(String textureName, PackedTiles texture) {
        VisTextButton button = new VisTextButton("Add Tile Set");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (textureName.equals("null")) return;

                String tilesetName = textureName + "_tileset";

                PixmapIO.writePNG(Gdx.files.absolute(
                        AppUtil.path(plugin.getAPI().getProjectPath(), "assets", "orig", "images", tilesetName + ".png"))
                        , GdxUtil.getPixmap(texture.getPixels())
                );

                Gdx.app.postRunnable(() -> {
                    plugin.getAPI().getCurrentProjectInfoVO().imagesPacks.get("main").regions.add(tilesetName);
//                    plugin.getAPI().saveProject();
                    plugin.getAPI().getFacade().sendNotification(MsgAPI.ACTION_REPACK);
                    plugin.getAPI().reLoadProject();
                });
            }
        });
        return button;
    }

    public void show(Stage uiStage) {
        UIDraggablePanel dialog = new UIDraggablePanel("Proto Tile Maker");
        dialog.setMovable(true);
        dialog.setModal(false);
        dialog.addCloseButton();
        dialog.add(this).fill().expand();
        dialog.pack();
        dialog.show(uiStage);
    }
}
