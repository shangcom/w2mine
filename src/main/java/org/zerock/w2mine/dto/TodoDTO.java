package org.zerock.w2mine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data // Getter, Setter, toString, equals 포함.
@NoArgsConstructor
public class TodoDTO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
