package com.example.test.utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class ResultPagedDto<T> {
    int page;
    int pageSize;
    Long totalPage;
    List<T> data;
}