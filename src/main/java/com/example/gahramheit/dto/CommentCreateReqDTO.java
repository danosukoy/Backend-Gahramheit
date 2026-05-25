package com.example.gahramheit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateReqDTO {

    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    @Size(max = 2000, message = "El comentario no puede exceder los 2000 caracteres")
    private String content;

    private Long parentId;
}
