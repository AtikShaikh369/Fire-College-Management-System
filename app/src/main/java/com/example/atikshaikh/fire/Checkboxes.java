package com.example.atikshaikh.fire;

import android.widget.CheckBox;

public class Checkboxes {
    String checkbox;
    Boolean status;

    public Checkboxes () {}
    public Checkboxes(String checkbox, Boolean status) {
        this.checkbox = checkbox;
        this.status = status;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
