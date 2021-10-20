package am.aca.bookingmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "partners")
@Getter
@Setter
@NoArgsConstructor
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "uuid")
    private String uuid;

    @OneToMany(mappedBy = "partner")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "partners")
    private List<Category> categories;

}
