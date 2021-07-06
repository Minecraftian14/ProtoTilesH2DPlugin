package com.mcxiv.plugin.view.subView;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.*;
import com.mcxiv.app.CachedTiles;
import com.mcxiv.app.PackedTiles;
import com.mcxiv.app.ProtoTiles_old;
import com.mcxiv.app.util.ArrUtil;
import com.mcxiv.app.util.Blending;
import com.mcxiv.plugin.util.GdxUtil;
import com.mcxiv.plugin.view.ProtoTilesView;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class SettingsPaneView extends VisTable {

    private final VisLabel lbl_padding;

    private final VisTextButton btn_apply;
    private final VisSlider fie_padding;
    private final VisCheckBox fie_transparent;
    private final VisSelectBox<String > fie_blender;

    private final ObjectMap<String, Blending> map = new ObjectMap<>();



    public SettingsPaneView(ProtoTilesView view) {
        add(new VisLabel("Hello World"));

        add(lbl_padding = new VisLabel("1", Align.right)).space(20).minWidth(40);
        add(fie_padding = new VisSlider(1, 100, 1, false)).space(20);
        fie_padding.addListener(GdxUtil.wrap(this::sliderChanged));
        fie_padding.setValue(20);

        fie_transparent = new VisCheckBox("Transparency Enabled");
        fie_transparent.setChecked(true);
        fie_transparent.addListener(GdxUtil.wrap(this::toggleCheckbox));
        add(fie_transparent).align(Align.left).space(20).minWidth(200);

        fie_blender = new VisSelectBox<>();
        for (Field field : Blending.class.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            if (!field.getType().getName().equals(Blending.class.getName()))
                if (Arrays.stream(field.getType().getInterfaces()).noneMatch(inter -> inter.getName().equals(Blending.class.getName())))
                    continue;
//            if (field.getType().equals(Blending.class) ||
//                    !Modifier.isStatic(field.getModifiers()) ||
//                    Arrays.stream(field.getType().getInterfaces()).noneMatch(clazz -> clazz.equals(Blending.class)))
//                continue;
            try {
                map.put(field.getName(), (Blending) field.get(null));
            } catch (Exception ignored) {
            }
        }
        fie_blender.setItems(map.keys().toArray());
        fie_blender.setSelected("defaultMix");
        add(fie_blender).space(20);

        btn_apply = new VisTextButton("Apply");
        btn_apply.addListener(GdxUtil.wrap(view::setViewAgain));

        add(btn_apply).space(20);
    }

    private void sliderChanged() {
        lbl_padding.setText("" + (int) fie_padding.getValue());
    }

    public PackedTiles getPackedTiles(TextureRegion region) {
        ArrUtil.blending = map.get(fie_blender.getSelected());
        ProtoTiles_old tiles = new ProtoTiles_old(GdxUtil.getPixels(region));
        tiles.setPadding(getPadding());
        tiles.setTransparencyEnabled(isTransparent());
        return new PackedTiles(new CachedTiles(tiles));
    }

    public int getPadding() {
        return (int) fie_padding.getValue();
    }

    public boolean isTransparent() {
        return fie_transparent.isChecked();
    }

    private void toggleCheckbox() {
        if (fie_transparent.isChecked())
            fie_transparent.setText("Transparency Enabled");
        else fie_transparent.setText("Transparency Disabled");
    }

    public void updateTransparencySlider(int width) {
        fie_padding.setRange(1, width);
    }

}
