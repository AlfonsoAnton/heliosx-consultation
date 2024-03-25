package com.heliosxconsultation.service;

import com.heliosxconsultation.data.dto.ConsultationQuestionDto;
import com.heliosxconsultation.data.entities.ConsultationQuestion;
import com.heliosxconsultation.data.repository.ConsultationQuestionRepository;
import com.heliosxconsultation.web.request.ConsultationQuestionRequest;
import com.heliosxconsultation.web.response.ConsultationQuestionResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ConsultationQuestionService {

  private final ConsultationQuestionRepository consultationQuestionRepository;

  public ConsultationQuestionService(
      ConsultationQuestionRepository consultationQuestionRepository) {
    this.consultationQuestionRepository = consultationQuestionRepository;
  }

  public List<ConsultationQuestionDto> findAllConsultationQuestions() {
    return consultationQuestionRepository.findAll().stream()
        .map(ConsultationQuestionDto::fromEntity).collect(
            Collectors.toList());
  }

  public ConsultationQuestionResponse createNewQuestion(ConsultationQuestionRequest consultationQuestionRequest) {
    var toPersist = new ConsultationQuestion();
    toPersist.setQuestion(consultationQuestionRequest.question());
    toPersist.setCategory(consultationQuestionRequest.category());
    toPersist.setVariableName(consultationQuestionRequest.variableName());
    toPersist.setAnswerForPrescription(consultationQuestionRequest.answerForPrescription());

    var persisted = consultationQuestionRepository.save(toPersist);

    return ConsultationQuestionResponse.fromDto(ConsultationQuestionDto.fromEntity(persisted));
  }
}
