package org.pursuit.psychictest.model;

public class Result {

    private boolean isCorrect;
    private long percentageCorrect;

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public long getPercentageCorrect() {
        return percentageCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setPercentageCorrect(long percentageCorrect) {
        this.percentageCorrect = percentageCorrect;
    }
}
