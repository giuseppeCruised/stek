package reWritten.domain.items;


import reWritten.utils.Log;

public class StringItem implements DataItem,PrintableItem{
    private String value;

    public StringItem(String value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object newValue) {
        this.value = (String) newValue;

    }

    @Override
    public void printItem() {
        Log.log(value);
    }
}
