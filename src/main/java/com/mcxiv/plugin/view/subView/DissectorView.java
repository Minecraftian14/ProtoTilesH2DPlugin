package com.mcxiv.plugin.view.subView;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.TileDissector;
import com.mcxiv.app.util.AppUtil;
import com.mcxiv.app.util.Color;
import com.mcxiv.plugin.util.GdxUtil;

public class DissectorView extends VisTable {
    public DissectorView(Color[][] pixels, SettingsPaneView settings) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addTitle("RECTANGLES WHICH ARE ALIGNED BY THE SIDES TOUCHING THREE SIDES");

        addImage(TileDissector.getTopRect(30, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRect(50, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        addImage(TileDissector.getBotRect(30, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRect(50, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        addImage(TileDissector.getLefRect(30, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getLefRect(50, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        addImage(TileDissector.getRigRect(30, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRigRect(50, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addTitle("RECTANGLES WHICH LYE IN BETWEEN THE IMAGE BUT TOUCHES TWO OPPOSITE SIDES");

        addImage(TileDissector.getHorRect(10, 30, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getHorRect(30, 10, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getVerRect(10, 30, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getVerRect(30, 10, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addTitle("RECTANGLES WHICH LYE INSIDE THE IMAGE AND TOUCHES NO SIDES");

        addImage(TileDissector.getRect(5, 10, 15, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRect(20, 0, 20, 0, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRect(20, 0, 0, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRect(0, 20, 20, 0, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRect(0, 20, 0, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addTitle("RECTANGLES WHICH LYE ALONG THE CORNERS TOUCHING TWO SIDES");

        addImage(TileDissector.getTopLefRect(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRigRect(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotLefRect(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRigRect(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addTitle("TRIANGLES WHICH ARE ALIGNED BY THE SIDES BUT TOUCH ONLY ONE SIDE || CROP TOOL ONLY CUTS TRIANGLES");

        addImage(TileDissector.getTopTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopTri(settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotTri(settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getLefTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getLefTri(settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRigTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRigTri(settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addTitle("TRIANGLES WHICH ARE ALIGNED BY THE CORNERS AND TOUCH ONLY TWO SIDES");

        addImage(TileDissector.getTopLefTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopLefTri(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRigTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRigTri(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotLefTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotLefTri(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRigTri(20, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRigTri(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        addTitle("");

        addImage(TileDissector.getTopLefTri(20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopLefTri(40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRigTri(20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRigTri(40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotLefTri(20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotLefTri(40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRigTri(20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRigTri(40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addTitle("THAT STRANGE POLYGON WHICH TOUCHES 2 SIDES AND HAS ONE INTERNAL ANGLE AS 135 DEGREES");
        addImage(TileDissector.getTopLefPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopLefPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRigPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getTopRigPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotLefPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotLefPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRigPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getBotRigPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

        addTitle("");


        addImage(TileDissector.getLefTopPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getLefTopPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRigTopPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRigTopPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getLefBotPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getLefBotPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRigBotPoly(20, 20, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));
        addImage(TileDissector.getRigBotPoly(40, 40, settings.isTransparent(), settings.getPadding(), pixels, AppUtil::intr, true, true));

    }

    private void addTitle(String s) {
        row();
        add(new VisLabel(s)).colspan(100).row();
    }

    private void addImage(Color[][] pixels) {
        add(new VisImageButton(new TextureRegionDrawable(GdxUtil.getTexture(pixels)))).space(10);
    }
}
