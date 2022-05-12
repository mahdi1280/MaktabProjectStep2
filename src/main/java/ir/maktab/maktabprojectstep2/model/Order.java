package ir.maktab.maktabprojectstep2.model;

import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME,name = "Orders")
public class Order extends BaseEntity {

    private int proposedPrice;
    private LocalDateTime createdAt;
    private String address;
    private StatusOrder status;
    private LocalDateTime wordTime;

    private Offer offer;
    private UnderService underService;
    private User user;

    public Order(int proposedPrice, String address, StatusOrder status, LocalDateTime wordTime, UnderService underService, User user) {
        this.proposedPrice = proposedPrice;
        this.address = address;
        this.status = status;
        this.wordTime = wordTime;
        this.underService = underService;
        this.user = user;
    }

    public Order() {
    }

    public static Builder builder(){
        return new Builder();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Enumerated(EnumType.STRING)
    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public LocalDateTime getWordTime() {
        return wordTime;
    }

    public void setWordTime(LocalDateTime wordTime) {
        this.wordTime = wordTime;
    }

    @ManyToOne
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @ManyToOne
    public UnderService getUnderService() {
        return underService;
    }

    public void setUnderService(UnderService underService) {
        this.underService = underService;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class Builder{

        private int proposedPrice;
        private String address;
        private StatusOrder status;
        private LocalDateTime wordTime;

        private UnderService underService;
        private User user;

        private Builder(){}

        public Builder proposedPrice(int proposedPrice){
            this.proposedPrice=proposedPrice;
            return this;
        }

        public Builder address(String address){
            this.address=address;
            return this;
        }

        public Builder status(StatusOrder status){
            this.status=status;
            return this;
        }

        public Builder wordTime(LocalDateTime wordTime){
            this.wordTime=wordTime;
            return this;
        }

        public Builder underService(UnderService underService){
            this.underService=underService;
            return this;
        }

        public Builder user(User user){
            this.user=user;
            return this;
        }

        public Order build(){
            return new Order(proposedPrice,address,status,wordTime,underService,user);
        }

    }
}
