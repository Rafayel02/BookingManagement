package am.aca.bookingmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToMany
    @JoinTable(
            name = "partners_activities",
            joinColumns = {@JoinColumn(name = "type_id")},
            inverseJoinColumns = {@JoinColumn(name = "partner_id")})
    private Set<Partner> partners;
}