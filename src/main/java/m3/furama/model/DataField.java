package m3.furama.model;

import java.util.ArrayList;

public class DataField {
    private String field;
    public ArrayList<Data> data;;


    public DataField() {
    }

    public DataField(String field, ArrayList<Data> data) {
        this.field = field;
        this.data = data;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}

