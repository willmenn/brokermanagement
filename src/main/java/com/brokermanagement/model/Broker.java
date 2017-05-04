package com.brokermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Broker {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "broker_id_seq")
    @SequenceGenerator(name = "broker_id_seq", sequenceName = "broker_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String manager;
}
