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

    //for test purposes

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (catalog != null ? !catalog.equals(product.catalog) : product.catalog != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        return result;
    }
}
