public class IntegerStream {
    int beginningValue;
    int currentValue;

    public IntegerStream(int beginning) {
        this.beginningValue = beginning;

        this.reset();
    }

    public int take() {
        int following = this.currentValue;

        this.currentValue = this.currentValue + 1;

        return following;
    }

    public IntegerStream reset() {
        this.currentValue = this.beginningValue;

        return this;
    }
}
