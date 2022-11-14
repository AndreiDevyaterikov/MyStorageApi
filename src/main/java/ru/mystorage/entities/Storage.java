package ru.mystorage.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@Table(name = "storages")
@NoArgsConstructor
public class Storage {

    @Id
    @GeneratedValue(generator = "storage_sequence")
    @Column(name = "storage_id")
    private Integer id;

    @Column(name = "storage_name")
    private String name;

}
