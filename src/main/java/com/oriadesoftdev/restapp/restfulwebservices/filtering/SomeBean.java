package com.oriadesoftdev.restapp.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.io.Serializable;
import java.util.Objects;

//@JsonIgnoreProperties("field2")
@JsonFilter(value = "SimpleBeanFilter")
public class SomeBean implements Serializable {

    public static final long serialVersionUID = -3299834943932497L;
    private String field1;

    private String field2;
    private String field3;

    public SomeBean() {

    }

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
//                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SomeBean someBean)) return false;
        return Objects.equals(getField1(), someBean.getField1()) && Objects.equals(getField2(), someBean.getField2()) && Objects.equals(getField3(), someBean.getField3());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getField1(), getField2(), getField3());
    }
}
