package org.incidence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {
    private int iconCategory;

    @JsonProperty("iconCategory")
    public int getIconCategory() { return iconCategory; }
    @JsonProperty("iconCategory")
    public void setIconCategory(int value) { this.iconCategory = value; }
}
