package com.irrigate.entities;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Slot")
@Data
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name ;


    @ManyToOne
    @JoinColumn(name = "plot_id")
    private Plot plot;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy="slot" , orphanRemoval = true)
    private List<TimeSlot> timeslots;

    @Override
    public String toString() {
        return  name;
    }
}
