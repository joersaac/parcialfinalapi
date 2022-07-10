package com.grupo16.src.models.dtos;

import javax.validation.constraints.Min;

public class PageableDTO {
    private String username;

    @Min(0)
    private int page;

    @Min(0)
    private int limit;

    public PageableDTO() {
        super();
        this.page = 0;
        this.limit = 10;
    }


    public PageableDTO(int page, int limit) {
        super();
        this.page = page;
        this.limit = limit;
    }

    public PageableDTO(String username, int page, int limit) {
        this.username = username;
        this.page = page;
        this.limit = limit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}