package ru.mystorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mystorage.entities.Product;
import ru.mystorage.entities.Storage;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String productName);
    Optional<Product> findByNameAndArticle(String name, String article);
    Optional<Product> findByStorage(Storage storage);
    List<Product> findAllByStorage(Storage storage);
}
