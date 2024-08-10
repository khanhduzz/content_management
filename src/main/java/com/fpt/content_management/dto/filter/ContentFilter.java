package com.fpt.content_management.dto.filter;

import com.fpt.content_management.util.PageSortUtil;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentFilter {

    String searchString;

    @Builder.Default
    Set<Long> contentIds = Collections.emptySet();

    @Pattern(regexp = "title|brief|username")
    @Builder.Default
    String orderBy = "title";

    @Pattern(regexp = "ASC|DESC|asc|desc")
    @Builder.Default
    String sortDir = "ASC";

    @Builder.Default
    Integer pageNumber = 1;

    @Builder.Default
    Integer pageSize = 1;

    public void setPageNumber (String pageNumber) {
        this.pageNumber = PageSortUtil.parsePageValue(pageNumber, 1);
    }

    public void setPageSize (String pageSize) {
        this.pageSize = PageSortUtil.parsePageValue(pageSize, 10);
    }
}
