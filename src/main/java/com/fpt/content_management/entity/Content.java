package com.fpt.content_management.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "Content.member",
        attributeNodes = @NamedAttributeNode("member")
)
public class Content extends AuditEntity<Long>{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String brief;

    @Column(name = "full_content", nullable = false, columnDefinition = "TEXT")
    private String fullContent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Member member;
}
