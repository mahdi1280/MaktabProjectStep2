package ir.maktab.maktabprojectstep2.service.user;

import ir.maktab.maktabprojectstep2.dto.request.UserSearchRequest;
import ir.maktab.maktabprojectstep2.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class UserSearch implements Specification<User> {

    private final UserSearchRequest userSearchRequest;

    public UserSearch(UserSearchRequest userSearchRequest) {
        this.userSearchRequest = userSearchRequest;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (Objects.nonNull(userSearchRequest.getEmail())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("email"), "%" + userSearchRequest.getEmail() + "%"));
        }
        if (Objects.nonNull(userSearchRequest.getFirstname())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("firstname"), "%" + userSearchRequest.getFirstname() + "%"));
        }
        if (Objects.nonNull(userSearchRequest.getLastname())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("lastname"), "%" + userSearchRequest.getLastname() + "%"));
        }
        if (Objects.nonNull(userSearchRequest.getRole())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("role"), userSearchRequest.getRole()));
        }
        if (Objects.nonNull(userSearchRequest.getUnderService())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("service"), userSearchRequest.getUnderService()));
        }
        return predicate;
    }
}
