package ir.maktab.maktabprojectstep2.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Service extends BaseEntity {

    private String title;

    public Service(String title){
        this.title=title;
    }

    public Service(){}

    public static Builder builder(){
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Builder{

        private String title;

        private Builder(){
        }

        public Builder title(String title){
            this.title=title;
            return this;
        }

        public Service build(){
            return new Service(title);
        }
    }
}
