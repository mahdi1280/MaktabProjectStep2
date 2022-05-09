package ir.maktab.maktabprojectstep2.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class UnderService extends BaseEntity {

    private int basePrice;
    private String details;
    private Service service;
    private Collection<User> users=new HashSet<>();

    public UnderService(int basePrice, String details, Service service) {
        this.basePrice = basePrice;
        this.details = details;
        this.service = service;
    }

    public UnderService() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @ManyToOne
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @ManyToMany(mappedBy = "services")
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public static class Builder{

        private int basePrice;
        private String details;
        private Service service;

        private Builder(){}

        public Builder basePrice(int basePrice){
            this.basePrice=basePrice;
            return this;
        }

        public Builder details(String details){
            this.details=details;
            return this;
        }

        public Builder service(Service service){
            this.service=service;
            return this;
        }

        public UnderService build(){
            return new UnderService(basePrice,details,service);
        }
    }
}
