package reWritten.domain.items;

public class BooleanItem implements DataItem{
    private Boolean value;

    public BooleanItem(Boolean value){
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object newValue) {
        assert newValue instanceof Boolean;
        this.value = (Boolean) newValue;
    }
}
