package com.heliosxconsultation.web.response;

import com.heliosxconsultation.data.dto.ConsultationQuestionDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConsultationQuestionResponse {
  String category;
  String question;
  String variableName;

  public static ConsultationQuestionResponse fromDto(ConsultationQuestionDto dto) {
    return ConsultationQuestionResponse.builder().category(dto.getCategory()).question(dto.getQuestion()).variableName(
        dto.getVariableName()).build();
  }
}
