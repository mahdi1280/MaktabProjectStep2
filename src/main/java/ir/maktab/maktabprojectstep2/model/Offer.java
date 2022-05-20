package ir.maktab.maktabprojectstep2.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Offer extends BaseEntity {

    private LocalDateTime periodOfTime;
    private int proposedPrice;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime startTime;

    @ManyToOne
    private Order order;
    @ManyToOne
    private User user;

}
