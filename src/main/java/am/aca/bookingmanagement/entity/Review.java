package am.aca.bookingmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews", uniqueConstraints = {
        @UniqueConstraint(name = "reviews_user_id_partner_id_un", columnNames = {"user_id", "partner_id"})
})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "partner_id", referencedColumnName = "id", nullable = false)
    private Partner partner;
}