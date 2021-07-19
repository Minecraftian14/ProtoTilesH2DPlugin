package com.mcxiv.app.cards;

import com.badlogic.gdx.utils.Array;
import com.mcxiv.app.util.Color;
import com.mcxiv.util.ProcessingCard;

public class FinalActionCard extends ProcessingCard<Color[][]> {

    private String title;

    public FinalActionCard(String title) {
        super(title);
        this.title = title;
    }

    @Override
    public ProcessingCard<Color[][]> clone() {
        FinalActionCard card = new FinalActionCard(title);
        Array<ProcessingCard<Color[][]>> children = getCards();
        for (int i = 0; i < children.size; i++) card.addCard(children.get(i).clone());
        return card;
    }

}
