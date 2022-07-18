package reWritten.domain.items;

import reWritten.utils.Log;

public class IntegerItem implements DataItem,PrintableItem {
    private Integer value;

    public IntegerItem(Integer value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object newValue) {
        this.value = (Integer) newValue;
    }

    @Override
    public void printItem(){
        Log.log(""+value);
    }
}
