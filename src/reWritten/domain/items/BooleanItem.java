package reWritten.domain.items;

import reWritten.utils.Log;

public class BooleanItem implements DataItem,PrintableItem{
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

    @Override
    public void printItem() {
        Log.log(value.toString());
    }
}
