package com.mcxiv.app.view.subView;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.*;
import com.mcxiv.app.CachedTiles;
import com.mcxiv.app.PackedTiles;
import com.mcxiv.app.util.ArrUtil;
import com.mcxiv.app.util.Blending;
import com.mcxiv.app.ui.StaticInstanceSelector;
import com.mcxiv.app.util.GdxUtil;
import com.mcxiv.app.view.ProtoTilesView;

public class SettingsPaneView extends VisTable {

    private final VisLabel lbl_padding;

    private final VisTextButton btn_apply;
    private final VisSlider fie_padding;
    private final VisCheckBox fie_transparent;
    private final StaticInstanceSelector<Blending> fie_blender;
    // TODO: fix the issue happening when you use a Interpolation such as elastic
    private final StaticInstanceSelector<Interpolation> fie_interpolator;

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

        fie_blender = new StaticInstanceSelector<>(Blending.class);
        fie_blender.setSelected("defaultMix");
        add(fie_blender).space(20);

        fie_interpolator = new StaticInstanceSelector<>(Interpolation.class);
        fie_interpolator.setSelected("pow3In");
        add(fie_interpolator).space(20);

        btn_apply = new VisTextButton("Apply");
        btn_apply.addListener(GdxUtil.wrap(view::setViewAgain));

        add(btn_apply).space(20);
    }

    private void sliderChanged() {
        lbl_padding.setText("" + (int) fie_padding.getValue());
    }

    public PackedTiles getPackedTiles(TextureRegion region) {
        return new PackedTiles(new CachedTiles(ArrUtil.clonePixels(GdxUtil.getPixels(region)), isTransparent(), getPadding(), fie_interpolator.getSelectedT()::apply, fie_blender.getSelectedT()));

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
