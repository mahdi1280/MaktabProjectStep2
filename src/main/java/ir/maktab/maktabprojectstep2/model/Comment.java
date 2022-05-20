package ir.maktab.maktabprojectstep2.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int score;
    private String details;

    @Version
    private int version;
    @OneToOne
    @MapsId
    private Offer offer;

}
