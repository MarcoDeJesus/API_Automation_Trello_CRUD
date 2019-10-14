package model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Deleted {
    @JsonProperty
    private String _value;

    public String get_value() {
        return _value;
    }

    public void set_value(String _value) {
        this._value = _value;
    }
}
