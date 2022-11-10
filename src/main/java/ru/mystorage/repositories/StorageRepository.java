package ru.mystorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mystorage.models.Storage;


@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {

}
