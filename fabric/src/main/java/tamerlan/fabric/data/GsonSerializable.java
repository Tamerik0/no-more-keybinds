package tamerlan.fabric.data;

public class GsonSerializable<T> {
    public T obj;
    public GsonSerializable(){}
    public GsonSerializable(T obj){
        this.obj = obj;
    }
}
