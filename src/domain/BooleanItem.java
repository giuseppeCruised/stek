package domain;

public class BooleanItem implements Item{
    private Boolean value;

    public BooleanItem(Boolean value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value =  (Boolean)value;
    }
}
