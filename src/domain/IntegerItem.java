package domain;

public class IntegerItem implements Item{
    private Integer value;

    public IntegerItem(Integer value){
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof Integer){
            this.value = (Integer) value;
        }else{
            System.out.println("TypeError: Integer");
        }
    }
}
