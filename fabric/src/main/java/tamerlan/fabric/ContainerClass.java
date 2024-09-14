package tamerlan.fabric;

import java.util.Collection;

public abstract class ContainerClass<V, T extends Collection<V>> implements IRemovable {
    @Override
    public void remove() {
        for(var i : _getContainer()){
            if(i instanceof IRemovable removable)
                removable.remove();
        }
        _getContainer().clear();
    }
    protected V _addElement(V element){
        _getContainer().add(element);
        return element;
    }
    protected V _removeElement(V element){
        _getContainer().remove(element);
        return element;
    }
    protected abstract T _getContainer();
}
