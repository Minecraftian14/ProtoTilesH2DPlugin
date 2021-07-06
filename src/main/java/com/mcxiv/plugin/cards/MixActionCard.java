package com.mcxiv.plugin.cards;

import com.badlogic.gdx.utils.Array;
import com.mcxiv.app.util.ArrUtil;
import com.mcxiv.app.util.Color;
import com.mcxiv.util.ProcessingCard;

public class MixActionCard extends ProcessingCard<Color[][]> {

    public static final String NAME = "Mix Action";

    public MixActionCard(ProcessingCard<Color[][]> parent) {
        super(NAME, parent);
    }

    @Override
    public Color[][] process(Color[][] colors) {
        Array<ProcessingCard<Color[][]>> cards = getCards();

        if (cards.size == 0) return colors;
        if (cards.size == 1) return cards.get(0).process(colors);

        Color[][] buff = cards.get(0).process(ArrUtil.clonePixels(colors));
        for (int i = 1, s = cards.size; i < s; i++)
            buff = ArrUtil.mix(buff, cards.get(i).process(ArrUtil.clonePixels(colors)));

        return buff;
    }

    @Override
    public ProcessingCard<Color[][]> clone() {
        MixActionCard card = new MixActionCard(getParentCard());
        Array<ProcessingCard<Color[][]>> children = getCards();
        for (int i = 0; i < children.size; i++) card.addCard(children.get(i).clone());
        return card;
    }
}
