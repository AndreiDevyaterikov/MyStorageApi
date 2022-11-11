package ru.mystorage.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "storages")
public class Storage {

    @Id
    @Column(name = "storage_id")
    private Integer id;

    @Column(name = "storage_name")
    private String name;

}
