package models;

import play.data.validation.Constraints;

public class Product {

    @Constraints.Required
    private String ean;

    @Constraints.Required
    private String name;

    private String description;

    public Product() {
    }

    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return String.format("%s - %s", ean, name);
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
