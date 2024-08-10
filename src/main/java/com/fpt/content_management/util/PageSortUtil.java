package com.fpt.content_management.util;

import com.fpt.content_management.constant.DefaultSortOption;
import com.fpt.content_management.exception.PaginateErrorException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public final class PageSortUtil {

    private PageSortUtil () {
    }

    public static Pageable createPageRequest (Integer pageNumber, Integer pageSize, Sort sort) {
        return PageRequest.of(pageNumber - 1, pageSize > 100 ? 100 : pageSize, sort);
    }

    public static Pageable createPageRequest (Integer pageNumber, Integer pageSize, String sortBy,
                                              Sort.Direction sortDirection, String defaultSortBy) {
        if (pageNumber == null || pageSize == null || sortBy == null) {
            throw new PaginateErrorException();
        }
        try {
            if (sortBy.isBlank()){
                sortBy = defaultSortBy;
            }
            Sort sort = Sort.by(sortDirection, sortBy);
            if (sortBy.equals("fullName")){
                sort = Sort.by(List.of(new Sort.Order(sortDirection, "firstName"), new Sort.Order(sortDirection, "lastName")));
            }
            return PageRequest.of(pageNumber, Math.min(pageSize, DefaultSortOption.MAX_PAGE_SIZE), sort);
        }
        catch (IllegalArgumentException e) {
            throw new PaginateErrorException();
        }
    }

    public static Sort.Direction parseSortDirection (String sortDirection) {
        try {
            return Sort.Direction.fromString(sortDirection);
        }
        catch (IllegalArgumentException e) {
            throw new PaginateErrorException();
        }
    }

    public static int parsePageValue (String value, int defaultValue) {
        try {
            int parsedValue = Integer.parseInt(value);
            return Math.max(parsedValue, 1);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
