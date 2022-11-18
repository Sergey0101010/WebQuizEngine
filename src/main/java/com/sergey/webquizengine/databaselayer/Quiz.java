package com.sergey.webquizengine.databaselayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quizzes")
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "text")
    private String text;

    @Size(min = 2)
    @ElementCollection
    @CollectionTable(name = "quiz_options",
    joinColumns = @JoinColumn(name = "quiz_id"))
    private List<String> options = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "quiz_answers",
            joinColumns = @JoinColumn(name = "quiz_id"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer = new ArrayList<>();


    @ManyToOne()
    @JoinColumn(name = "answered_user_id")
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "quiz")
    private List<QuizAnswer> quizAnswers;


}
