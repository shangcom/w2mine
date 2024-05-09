package org.zerock.w2mine.domain;


import lombok.*;

import java.time.LocalDate;

@Getter // 읽기 전용. vo는 데이터 그 자체를 의미. setter 없음.
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
