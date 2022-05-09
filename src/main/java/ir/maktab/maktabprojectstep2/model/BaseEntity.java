package ir.maktab.maktabprojectstep2.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    private long id;
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public BaseEntity setId(long id) {
        this.id = id;
        return this;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public BaseEntity setVersion(int version) {
        this.version = version;
        return this;
    }
}
