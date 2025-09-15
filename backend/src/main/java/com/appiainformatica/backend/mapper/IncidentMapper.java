package com.appiainformatica.backend.mapper;

import com.appiainformatica.backend.dto.request.IncidentRequestDTO;
import com.appiainformatica.backend.dto.response.IncidentResponseDTO;
import com.appiainformatica.backend.model.Incident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidentMapper {

    Incident toEntity(IncidentRequestDTO incidentRequestDTO);

    IncidentResponseDTO toDto(Incident incident);
}
