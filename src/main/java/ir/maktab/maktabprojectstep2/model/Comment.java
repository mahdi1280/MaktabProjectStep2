package ir.maktab.maktabprojectstep2.model;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Comment extends BaseEntity {

    private int score;
    private String details;

    private Offer offer;

    public Comment(int score, String details, Offer offer) {
        this.score = score;
        this.details = details;
        this.offer = offer;
    }

    public Comment() {
    }

    public static Builder builder(){
        return new Builder();
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @OneToOne
    @MapsId
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public static class Builder{

        private int score;
        private String details;
        private Offer offer;

        private Builder(){
        }

        public Builder score(int score){
            this.score=score;
            return this;
        }

        public Builder details(String details){
            this.details=details;
            return this;
        }

        public Builder offer(Offer offer){
            this.offer=offer;
            return this;
        }

        public Comment build(){
            return new Comment(score,details,offer);
        }

    }
}
