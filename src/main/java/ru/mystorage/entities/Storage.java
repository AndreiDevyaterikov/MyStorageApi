package ru.mystorage.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "storages")
public class Storage {

    @Id
    @GeneratedValue(generator = "storage_sequence")
    @Column(name = "storage_id")
    private Integer id;

    @Column(name = "storage_name")
    private String name;

}
