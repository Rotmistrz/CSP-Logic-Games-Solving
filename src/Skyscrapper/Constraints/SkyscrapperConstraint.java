package Skyscrapper.Constraints;

import BoardGames.BoardConstraint;

abstract public class SkyscrapperConstraint implements BoardConstraint {
    protected int visibleAmount;
    protected int line;

    public SkyscrapperConstraint(int line, int visibleAmount) {
        this.line = line;
        this.visibleAmount = visibleAmount;
    }

    public SkyscrapperConstraint setLine(int i) {
        this.line = i;

        return this;
    }

    public SkyscrapperConstraint setVisibleAmount(int i) {
        this.visibleAmount = i;

        return this;
    }
}