package com.gabrielalbernazdev.techforumjavaweb.web.viewobject;

public class FormField {
    private final String label;
    private final String type;
    private final String id;
    private final String name;
    private final boolean required;
    private String value;

    public FormField(String label, String type, String id, String name, boolean required) {
        this.label = label;
        this.type = type;
        this.id = id;
        this.name = name;
        this.required = required;
    }

    public String getLabel() { return label; }
    public String getType() { return type; }
    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isRequired() { return required; }
    public String getValue() { return value; }

    public void setValue(String value) {
        this.value = value;
    }
}
