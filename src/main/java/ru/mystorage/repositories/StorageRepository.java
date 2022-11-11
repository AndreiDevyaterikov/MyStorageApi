package ru.mystorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mystorage.entities.Storage;

import java.util.Optional;


@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
    Optional<Storage> findByName(String storageName);
}
