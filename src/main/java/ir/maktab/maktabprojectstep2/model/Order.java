package ir.maktab.maktabprojectstep2.model;

import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME, name = "Orders")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    private int proposedPrice;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String address;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    private LocalDateTime wordTime;
    private Double longitute;
    private Double latitute;

    @ManyToOne(fetch = FetchType.LAZY)
    private Offer offer;
    @ManyToOne(fetch = FetchType.LAZY)
    private UnderService underService;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
