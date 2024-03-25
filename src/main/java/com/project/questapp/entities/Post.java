package com.project.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    Long id;
    /** FetchType.LAZY User objesinin databaseden hemen çekme
     yani post objesini cektiginde ilgili userı bana getirmene gerek yok
     fetchtype.eager kullansaydım ilgili userı bana getirirdi.
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // ilgili user gittiğinde postları da silinsin
    @JsonIgnore
    User user;

    String tittle;
    @Lob
    @Column(columnDefinition = "text")
    String text;

}
