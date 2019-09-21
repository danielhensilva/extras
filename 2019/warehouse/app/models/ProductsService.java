package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsService {

    private static List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product("1111111111111", "Paperclips 1", "Paperclips description 1"));
        products.add(new Product("2222222222222", "Paperclips 2", "Paperclips description "));
        products.add(new Product("3333333333333", "Paperclips 3", "Paperclips description 3"));
        products.add(new Product("4444444444444", "Paperclips 4", "Paperclips description 4"));
        products.add(new Product("5555555555555", "Paperclips 5", "Paperclips description 5"));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findByEan(String ean) {
        return products.stream()
                .filter(Predicates.byEan(ean))
                .findFirst();
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(Predicates.byName(name))
                .collect(Collectors.toList());
    }

    public boolean remove(Product product) {
        return products.remove(product);
    }

    public void save(Product product) {
        findByEan(product.ean)
                .ifPresent(p -> products.remove(p));
        products.add(product);
    }

    private static class Predicates {

        private static Predicate<Product> byEan(String ean) {
            return p -> p.ean.equals(ean);
        }

        private static Predicate<Product> byName(String name) {
            return p -> p.name.toLowerCase().contains(name.toLowerCase());
        }

    }

}
