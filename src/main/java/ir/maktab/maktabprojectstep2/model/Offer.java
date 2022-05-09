package ir.maktab.maktabprojectstep2.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Offer extends BaseEntity {

    private LocalDateTime periodOfTime;
    private int proposedPrice;
    private LocalDateTime createdAt;
    private LocalDateTime startTime;

    private Order order;
    private User user;

    public Offer(LocalDateTime periodOfTime, int proposedPrice, LocalDateTime startTime, Order order, User user) {
        this.periodOfTime = periodOfTime;
        this.proposedPrice = proposedPrice;
        this.startTime = startTime;
        this.order = order;
        this.user = user;
    }

    public Offer() {
    }

    public static Builder builder(){
        return new Builder();
    }


    public LocalDateTime getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(LocalDateTime periodOfTime) {
        this.periodOfTime = periodOfTime;
    }

    public int getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(int proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    @CreationTimestamp
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @ManyToOne
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class Builder{

        private LocalDateTime periodOfTime;
        private int proposedPrice;
        private LocalDateTime startTime;

        private Order order;
        private User user;

        private Builder(){

        }

        public Builder periodOfTime(LocalDateTime periodOfTime){
            this.periodOfTime=periodOfTime;
            return this;
        }

        public Builder proposedPrice(int proposedPrice){
            this.proposedPrice=proposedPrice;
            return this;
        }

        public Builder startTime(LocalDateTime startTime){
            this.startTime=startTime;
            return this;
        }

        public Builder order(Order order){
            this.order=order;
            return this;
        }

        public Builder user(User user){
            this.user=user;
            return this;
        }

        public Offer build(){
            return new Offer(periodOfTime,proposedPrice,startTime,order,user);
        }
    }
}
