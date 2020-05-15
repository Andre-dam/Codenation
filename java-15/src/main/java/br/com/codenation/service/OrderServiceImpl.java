package br.com.codenation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    /**
     * Calculate the sum of all OrderItems
     */
    @Override
    public Double calculateOrderValue(List<OrderItem> items) {
        return items.stream()
                .map(item -> productRepository.findById(item.getProductId())
                        .map(product -> product.getIsSale() ? product.getValue() * 0.8 : product.getValue())
                        .map(value -> value * item.getQuantity())
                        .orElse(0.0)
                ).reduce(0.0, Double::sum);
    }

    /**
     * Map from idProduct List to Product Set
     */
    @Override
    public Set<Product> findProductsById(List<Long> ids) {
        return ids.stream()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    /**
     * Calculate the sum of all Orders(List<OrderIten>)
     */
    @Override
    public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
        return orders.stream()
                .map(this::calculateOrderValue)
                .reduce(0.0, Double::sum) ;
    }

    /**
     * Group products using isSale attribute as the map key
     */
    @Override
    public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(Product::getIsSale, this::valueMap, this::mergeValues));
    }

    public List<Product> valueMap(Product product){
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    public List<Product> mergeValues(List<Product> currentList, List<Product> newList){
        currentList.add(newList.get(0));
        return currentList;
    }
}