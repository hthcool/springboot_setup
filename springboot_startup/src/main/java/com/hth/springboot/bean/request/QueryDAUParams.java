package com.hth.springboot.bean.request;

import lombok.Data;

import java.util.List;

@Data
public class QueryDAUParams {
    private List<String> day;
}
