package reWritten.domain.items;


import reWritten.utils.Log;

public class CharItem implements DataItem,PrintableItem{
    private Character value;


    public CharItem(Character value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object newValue) {
        value = (Character) newValue;
    }

    @Override
    public void printItem() {
        Log.log(""+value);
    }
}
