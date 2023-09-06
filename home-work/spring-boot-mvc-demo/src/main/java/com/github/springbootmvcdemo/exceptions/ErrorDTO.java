package com.github.springbootmvcdemo.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDTO {
    private Date timestamp;
    private int status;
    private String path;
    private List<String> errs = new ArrayList<>();

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getErrs() {
        return errs;
    }

    public void setErrs(List<String> errs) {
        this.errs = errs;
    }

    public void addErr(String err) {
        this.errs.add(err);
    }
}
