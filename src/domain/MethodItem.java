package domain;

public class MethodItem implements Item{
    private String value;

    public MethodItem(String value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.METHOD;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof String){
            this.value = (String) value;
        }else{
            System.out.println("TypeError: Method");
        }
    }
}
