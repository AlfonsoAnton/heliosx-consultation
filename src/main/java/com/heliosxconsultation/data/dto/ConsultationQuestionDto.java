package com.heliosxconsultation.data.dto;

import com.heliosxconsultation.data.entities.ConsultationQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ConsultationQuestionDto {
  String category;
  String question;
  String answerForPrescription;
  String variableName;

  public static ConsultationQuestionDto fromEntity(ConsultationQuestion entity) {
    return ConsultationQuestionDto.builder().category(entity.getCategory()).question(entity.getQuestion()).answerForPrescription(
        entity.getAnswerForPrescription()).variableName(entity.getVariableName()).build();
  }
}
