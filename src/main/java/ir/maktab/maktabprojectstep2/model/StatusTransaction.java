package ir.maktab.maktabprojectstep2.model;

import ir.maktab.maktabprojectstep2.idpay.StatusType;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Immutable
@Table(schema = Schema.SCHEMA_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusTransaction extends BaseEntity {

    private String title;
    @Enumerated(EnumType.STRING)
    private StatusType code;

}
