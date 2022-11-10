package ru.mystorage.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Storage {
    @Id
    private Integer id;
    private String name;

}
