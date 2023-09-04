package se.lexicon;

import java.util.List;

/**
 *
 *
 */
public class App 
{
    public interface Action {
        void execute(Product p);
    }
    public interface Conditional {
        boolean test(Product p);
    }
    public static void processProducts(List<Product> productList, Conditional condition, Action action) {
        for (Product product : productList) {
            if (condition.test(product)) {
                action.execute(product);
            }
        }
    }

    public static void main( String[] args )
    {
        // Create a list of products
        List<Product> productList = List.of(
                new Product("Laptop", 1200, 0),
                new Product("TV", 800, 11),
                new Product("Fridge", 1300, 5),
                new Product("Barista", 400, 65),
                new Product("Tooth brush", 125, 0)
        );

        // Scenario 1: Print out all Products that have stock value of 0.
        processProducts(productList, product -> product.getStock() == 0, product -> System.out.println("0 in stock :" + product.getProductName()));

        // Scenario 2: Print out the productName of all the Products that start with B.
        processProducts(productList, product -> product.getProductName().startsWith("B"),
                product -> System.out.println("Product(s) that starts with a B : " + product.getProductName()));

        // Scenario 3: Print out all Products that have price above 100 AND lower than 150.
        processProducts(productList, product -> product.getPrice() > 100 && product.getPrice() < 150,
                product -> System.out.println("Pricetag between 100 and 150 : " + product.getProductName() + " at " + product.getPrice()));

        // Scenario 4: Increase the price of all Products that have stock value of less than 10 AND above 0 by 50%.
        processProducts(productList, product -> product.getStock() > 0 && product.getStock() < 10,
                product -> {
                    double newPrice = product.getPrice() * 1.5; // Increase price by 50%
                    product.setPrice(newPrice);
                    System.out.println("New price for the " + product.getProductName() + " = " + product.getPrice());
                });
    }
}
