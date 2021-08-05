package com.mcxiv.app.cards;

import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.util.ArrUtil;
import com.mcxiv.app.util.Blending;
import com.mcxiv.app.util.Color;
import com.mcxiv.app.ui.StaticInstanceSelector;
import com.mcxiv.util.ProcessingCard;

public class MixActionCard extends ProcessingCard<Color[][]> {

    public static final String NAME = "Mix Action";

    private final StaticInstanceSelector<Blending> fie_blending;

    public MixActionCard(ProcessingCard<Color[][]>...children) {
        super(NAME, children);

        VisTable settings = getSettings();

        settings.add(new VisLabel("Blender")).padRight(internal_pad);
        settings.add(fie_blending = new StaticInstanceSelector<>(Blending.class)).growX();
        fie_blending.setSelected("defaultMix");
    }

    @Override
    public Color[][] process(Color[][] colors) {
        Array<ProcessingCard<Color[][]>> cards = getCards();

        if (cards.size == 0) return colors;
        if (cards.size == 1) return cards.get(0).process(colors);

        Blending blender = fie_blending.getSelectedT();
        Color[][] buff = cards.get(0).process(ArrUtil.clonePixels(colors));

        for (int i = 1, s = cards.size; i < s; i++)
            buff = ArrUtil.mix(buff, cards.get(i).process(ArrUtil.clonePixels(colors)), blender);

        return buff;
    }

    @Override
    public ProcessingCard<Color[][]> clone() {
        MixActionCard card = ProcessingCard.clone(this, new MixActionCard());
        card.fie_blending.setSelected(fie_blending.getSelected());
        return card;
    }
}
