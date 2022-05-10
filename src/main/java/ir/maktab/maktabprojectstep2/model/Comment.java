package ir.maktab.maktabprojectstep2.model;

import javax.persistence.*;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Comment {

    private long id;
    private int score;
    private String details;
    private int version;

    private Offer offer;

    public Comment(long  id,int score, String details, Offer offer) {
        this.id=id;
        this.score = score;
        this.details = details;
        this.offer = offer;
    }

    public Comment() {
    }

    public static Builder builder(){
        return new Builder();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

        private long id;
        private int score;
        private String details;
        private Offer offer;

        private Builder(){
        }

        public Builder id(long id){
            this.id=id;
            return this;
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
            return new Comment(id,score,details,offer);
        }

    }
}
