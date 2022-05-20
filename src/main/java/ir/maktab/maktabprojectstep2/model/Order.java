package ir.maktab.maktabprojectstep2.model;

import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME,name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {

    private int proposedPrice;
    private LocalDateTime createdAt;
    private String address;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    @CreationTimestamp
    private LocalDateTime wordTime;

    @ManyToOne
    private Offer offer;
    @ManyToOne
    private UnderService underService;
    @ManyToOne
    private User user;

}
