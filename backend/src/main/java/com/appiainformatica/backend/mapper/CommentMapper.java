package com.appiainformatica.backend.mapper;

import com.appiainformatica.backend.dto.request.CommentRequestDTO;
import com.appiainformatica.backend.dto.response.CommentResponseDTO;
import com.appiainformatica.backend.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentRequestDTO commentRequestDTO);

    CommentResponseDTO toDto(Comment comment);
}
