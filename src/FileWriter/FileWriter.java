package FileWriter;

import DataWrapper.DataWrapper;

import java.util.ArrayList;


public abstract class FileWriter <E extends DataWrapper> {

    public abstract void write(ArrayList<E> list);



}
