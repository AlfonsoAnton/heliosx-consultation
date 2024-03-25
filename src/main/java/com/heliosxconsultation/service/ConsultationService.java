package com.heliosxconsultation.service;

import com.heliosxconsultation.QuestionNotAnsweredException;
import com.heliosxconsultation.data.dto.ConsultationQuestionDto;
import com.heliosxconsultation.web.response.ConsultationResultResponse;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ConsultationService {

  private final ConsultationQuestionService consultationQuestionService;

  public ConsultationService(ConsultationQuestionService consultationQuestionService) {
    this.consultationQuestionService = consultationQuestionService;
  }

  public ConsultationResultResponse determinePrescriptionStatus(Map<String, String> data) {
    var consultationQuestions = consultationQuestionService.findAllConsultationQuestions();

    if (consultationQuestions.size() != data.size()) {
      throw new QuestionNotAnsweredException();
    }

    consultationQuestions.forEach(consultationQuestion -> {
      if (!data.containsKey(consultationQuestion.getVariableName())) {
        throw new QuestionNotAnsweredException();
      }
    });

    return new ConsultationResultResponse(checkAnswersToQuestions(consultationQuestions, data));
  }

  private boolean checkAnswersToQuestions(List<ConsultationQuestionDto> questions, Map<String, String> answers) {
    for (ConsultationQuestionDto question: questions) {

      var fetchedQuestionVariableName = question.getVariableName();
      var answer = answers.get(fetchedQuestionVariableName);

      if (!question.getAnswerForPrescription().equalsIgnoreCase(answer)) {
        log.warn("Question {} failed required answer {}", question.getVariableName(), question.getAnswerForPrescription());
        return false;
      }
    }
    return true;
  }
}
