package com.mcxiv.app.cards;

import com.badlogic.gdx.utils.Array;
import com.mcxiv.app.util.ArrUtil;
import com.mcxiv.app.util.Color;
import com.mcxiv.util.ProcessingCard;

public class FinalActionCard extends ProcessingCard<Color[][]> {

    private String title;

    @SafeVarargs
    public FinalActionCard(String title, ProcessingCard<Color[][]>... children) {
        super(title, children);
        this.title = title;
    }

    public FinalActionCard(String title) {
        super(title);
        this.title = title;
    }

    @Override
    public Color[][] process(Color[][] colors) {
        return ArrUtil.deNull(super.process(colors), Color.BLACK);
    }

    @Override
    public ProcessingCard<Color[][]> clone() {
        return ProcessingCard.clone(this, new FinalActionCard(title));
    }

}
