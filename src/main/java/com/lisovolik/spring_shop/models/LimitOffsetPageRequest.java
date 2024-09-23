package com.lisovolik.spring_shop.models;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by Alexandr Lisovolik on  23.09.2024
 */

public class LimitOffsetPageRequest implements Pageable {
    private int limit;
    private int offset;
    private final Sort sort;

    public LimitOffsetPageRequest(int offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }
    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new LimitOffsetPageRequest((int) getOffset() + getPageSize(), getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();

    }

    @Override
    public Pageable first() {
        return new LimitOffsetPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new LimitOffsetPageRequest(pageNumber, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    public LimitOffsetPageRequest previous() {
        return hasPrevious() ? new LimitOffsetPageRequest((int)getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }

}
