package com.heliosxconsultation.data.entities;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "ConsultationQuestions")
@Data
public class ConsultationQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String category;
  @Column(columnDefinition = "text")
  private String question;
  private String answerForPrescription;
  private String variableName;
}
