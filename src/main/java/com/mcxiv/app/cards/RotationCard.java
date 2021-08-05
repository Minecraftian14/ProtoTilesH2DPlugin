package com.mcxiv.app.cards;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.util.ArrUtil;
import com.mcxiv.app.util.Color;
import com.mcxiv.util.ProcessingCard;

public class RotationCard extends ProcessingCard<Color[][]> {

    public static final String NAME = "Rotate";

    private final VisSelectBox<String> mode;
    private int degrees;

    @SafeVarargs
    public RotationCard(int degrees, ProcessingCard<Color[][]>...children) {
        super(NAME);
        this.degrees = degrees;

        VisTable settings = getSettings();

        settings.add(new VisLabel("Rotation")).padRight(internal_pad);
        settings.add(mode = new VisSelectBox<>()).growX();
        mode.setItems("None", "90 degree", "180 degree", "270 degree");
        if (degrees == 0) mode.setSelected("None");
        else if (degrees == 90) mode.setSelected("90 degree");
        else if (degrees == 180) mode.setSelected("180 degree");
        else if (degrees == 270) mode.setSelected("270 degree");

        for (ProcessingCard<Color[][]> child : children) addCard(child);
    }

    @Override
    public Color[][] process(Color[][] colors) {
        colors = super.process(colors);
        switch (mode.getSelected()) {
            case "90 degree":
                return ArrUtil.rot90(colors);
            case "180 degree":
                return ArrUtil.rot180(colors);
            case "270 degree":
                return ArrUtil.rot270(colors);
            default:
            case "none":
                return ArrUtil.clonePixels(colors);
        }
    }

    @Override
    public ProcessingCard<Color[][]> clone() {
        return ProcessingCard.clone(this, new RotationCard(degrees));
    }
}
