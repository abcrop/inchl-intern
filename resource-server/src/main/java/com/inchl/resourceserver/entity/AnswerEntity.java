package com.inchl.resourceserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "answers")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity {
    @Id
    @GeneratedValue
    Long id;
    String answer;

    @ManyToOne
    @JoinColumn(name = "q_id")
    QuestionEntity question;

}
