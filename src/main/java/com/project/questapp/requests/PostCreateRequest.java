package com.project.questapp.requests;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PostCreateRequest {

    @Id
    Long id;
    String text;
    String title;
    Long userId;
}
