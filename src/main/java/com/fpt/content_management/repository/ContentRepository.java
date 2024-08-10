package com.fpt.content_management.repository;

import com.fpt.content_management.entity.Content;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {
    Content findContentByTitle (String title);

    @EntityGraph(value = "Content.member", type = EntityGraph.EntityGraphType.LOAD)
    List<Content> findAll ();

    @EntityGraph(value = "Content.member", type = EntityGraph.EntityGraphType.LOAD)
    Content findContentById (Long id);
}
