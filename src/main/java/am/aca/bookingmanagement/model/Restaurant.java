package am.aca.bookingmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "image", nullable = true)
    private String image;

    @ManyToMany(mappedBy = "restaurants")
    private List<Category> categories;

    @OneToMany(mappedBy = "restaurants")
    private List<Reviews> reviews;
}
