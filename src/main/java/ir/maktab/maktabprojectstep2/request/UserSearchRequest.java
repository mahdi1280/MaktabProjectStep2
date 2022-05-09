package ir.maktab.maktabprojectstep2.request;

import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.enums.Role;

public class UserSearchRequest {

    private final Role role;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final UnderService underService;

    public UserSearchRequest(Role role, String firstname, String lastname, String email, UnderService underService) {
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.underService = underService;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Role getRole() {
        return role;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public UnderService getUnderService() {
        return underService;
    }

    public static class Builder{

        private Role role;
        private String firstname;
        private String lastname;
        private String email;
        private UnderService underService;

        private Builder(){}

        public Builder role(Role role){
            this.role=role;
            return this;
        }

        public Builder firstname(String firstname){
            this.firstname=firstname;
            return this;
        }

        public Builder lastname(String lastname){
            this.lastname=lastname;
            return this;
        }

        public Builder email(String email){
            this.email=email;
            return this;
        }

        public Builder underService(UnderService underService){
            this.underService=underService;
            return this;
        }

        public UserSearchRequest build(){
            return new UserSearchRequest(role,firstname,lastname,email,underService);
        }

    }
}
