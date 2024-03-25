package com.heliosxconsultation.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.heliosxconsultation.service.ConsultationQuestionService;
import com.heliosxconsultation.service.ConsultationService;
import com.heliosxconsultation.web.request.ConsultationQuestionRequest;
import com.heliosxconsultation.web.request.ConsultationSubmissionRequest;
import com.heliosxconsultation.web.response.ConsultationQuestionResponse;
import com.heliosxconsultation.web.response.ConsultationQuestionsResponse;
import com.heliosxconsultation.web.response.ConsultationResultResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/consultation")
public class ConsultationController {

  private final ConsultationService consultationService;
  private final ConsultationQuestionService consultationQuestionService;

  public ConsultationController(ConsultationService consultationService,
      ConsultationQuestionService consultationQuestionService) {
    this.consultationService = consultationService;
    this.consultationQuestionService = consultationQuestionService;
  }

  @GetMapping(value = "/questions", produces = APPLICATION_JSON_VALUE)
  public ConsultationQuestionsResponse  getConsultationQuestions() {
    var questions = consultationQuestionService.findAllConsultationQuestions();
    return new ConsultationQuestionsResponse(questions.stream().map(ConsultationQuestionResponse::fromDto).collect(Collectors.toList()));
  }

  @PostMapping(consumes = "application/json", produces = APPLICATION_JSON_VALUE)
  public ConsultationResultResponse submitConsultationQuestions(@RequestBody ConsultationSubmissionRequest request) {
   return consultationService.determinePrescriptionStatus(request.getAnswers());
  }

  @PostMapping(value = "/questions", consumes = "application/json", produces = APPLICATION_JSON_VALUE)
  public ConsultationQuestionResponse createNewConsultationQuestion(@RequestBody ConsultationQuestionRequest request) {
    return consultationQuestionService.createNewQuestion(request);
  }
}