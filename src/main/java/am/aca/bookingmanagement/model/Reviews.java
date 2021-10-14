package am.aca.bookingmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="reviews", uniqueConstraints = {
        @UniqueConstraint(name = "user_restaurant_review", columnNames = {"user_id", "restaurant_id"})
})
@Setter
@Getter
public class Reviews implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "rating", nullable = true)
    int rating;

    @Column(name = "comment", nullable = true)
    String comment;

    @ManyToMany(mappedBy = "reviewSet")
    private Set<Category> categories = new HashSet<>();


    public void setRating(int rating) {
        if(rating >= 0 && rating <= 6) {
            this.rating = rating;
        } else throw new IllegalArgumentException();
    }
}
