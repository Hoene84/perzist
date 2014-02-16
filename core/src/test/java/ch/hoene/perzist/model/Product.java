package ch.hoene.perzist.model;

public class Product {

    final int id;
    final String description;
    final String catalog;

    public Product(int id, String description, String catalog) {
        this.id = id;
        this.description = description;
        this.catalog = catalog;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCatalog() {
        return catalog;
    }
}
