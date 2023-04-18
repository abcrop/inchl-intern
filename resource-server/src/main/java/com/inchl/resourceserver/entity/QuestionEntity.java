package com.inchl.resourceserver.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "questions")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    @Id
    @GeneratedValue
    Long id;
    String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, targetEntity = AnswerEntity.class )
    List<AnswerEntity> answers;

}
