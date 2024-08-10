package com.fpt.content_management.service.impl;

import com.fpt.content_management.entity.Content;
import com.fpt.content_management.entity.Member;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public final class ContentSpecification {

    private ContentSpecification() {
    }

    public static Specification<Content> hasTitle (String title) {
        String lowerCaseName = title == null ? "": title.trim().toLowerCase();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + lowerCaseName + "%");
    }

    public static Specification<Content> hasBrief (String brief) {
        String lowerCaseName = brief == null ? "": brief.trim().toLowerCase();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get("brief")), "%" + lowerCaseName + "%");
    }

    public static Specification<Content> hasAuthor (String username) {
        String lowerCaseName = username == null ? "": username.trim().toLowerCase();
        return (root, query, criteriaBuilder) -> {
            Join<Content, Member> accountJoin = root.join("member");
            return criteriaBuilder.like(criteriaBuilder.lower(accountJoin.get("username")), "%" + lowerCaseName + "%");
        };
    }
}
