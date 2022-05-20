package ir.maktab.maktabprojectstep2.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Builder
@Table(schema = Schema.SCHEMA_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnderService extends BaseEntity {

    private int basePrice;
    private String details;
    @ManyToOne
    private Service service;
    @ManyToMany(mappedBy = "services")
    private Collection<User> users=new HashSet<>();

}
