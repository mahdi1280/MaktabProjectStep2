package ir.maktab.maktabprojectstep2.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OfferFindByOrderResponse implements Serializable {

    private final long id;
    private final LocalDateTime periodOfTime;
    private final int proposedPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime startTime;
    private final long userId;

    @JsonCreator
    public OfferFindByOrderResponse(long id, LocalDateTime periodOfTime, int proposedPrice, LocalDateTime createdAt, LocalDateTime startTime, long userId) {
        this.id = id;
        this.periodOfTime = periodOfTime;
        this.proposedPrice = proposedPrice;
        this.createdAt = createdAt;
        this.startTime = startTime;
        this.userId = userId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getPeriodOfTime() {
        return periodOfTime;
    }

    public int getProposedPrice() {
        return proposedPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public long getUserId() {
        return userId;
    }

    public static class Builder{

        private long id;
        private LocalDateTime periodOfTime;
        private int proposedPrice;
        private LocalDateTime createdAt;
        private LocalDateTime startTime;
        private long userId;

        private Builder(){}

        public Builder id(long id){
            this.id=id;
            return this;
        }

        public Builder periodOfTime(LocalDateTime periodOfTime){
            this.periodOfTime=periodOfTime;
            return this;
        }

        public Builder proposedPrice(int proposedPrice){
            this.proposedPrice=proposedPrice;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt){
            this.createdAt=createdAt;
            return this;
        }

        public Builder startTime(LocalDateTime startTime){
            this.startTime=startTime;
            return this;
        }

        public Builder userId(long userId){
            this.userId=userId;
            return this;
        }

        public OfferFindByOrderResponse build(){
            return new OfferFindByOrderResponse(id,periodOfTime,proposedPrice,createdAt,startTime,userId);
        }
    }
}
