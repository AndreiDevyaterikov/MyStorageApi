package ru.mystorage.entities;

import lombok.*;

import javax.persistence.*;

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
