package com.heliosxconsultation.web.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.heliosxconsultation.data.repository.ConsultationQuestionRepository;
import com.heliosxconsultation.web.request.ConsultationQuestionRequest;
import com.heliosxconsultation.web.request.ConsultationSubmissionRequest;
import com.heliosxconsultation.web.response.ConsultationQuestionResponse;
import com.heliosxconsultation.web.response.ConsultationQuestionsResponse;
import com.heliosxconsultation.web.response.ConsultationResultResponse;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultationControllerSpringBootTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private ConsultationQuestionRepository consultationQuestionRepository;

  @Test
  void testGetAllQuestionContainsAllQuestions() {
    var response = testRestTemplate.getForObject("http://localhost:" + port + "/api/consultation/questions",
        ConsultationQuestionsResponse.class);

    assertThat(response.questionResponseList()).hasSize(11);
  }

  @Test
  void testSubmitConsultationQuestionsAllQuestionsAnsweredCorrectlyReturnsLikelyPrescription() {
    var requestObject = new ConsultationSubmissionRequest();
    var requestMap = new HashMap<String, String>(11);

    requestMap.put("sneezes", "yes");
    requestMap.put("allergicReactionToMedication", "no");
    requestMap.put("pregnantOrBreastFeeding", "no");
    requestMap.put("hadNasalSurgery", "no");
    requestMap.put("conflictingDiagnosis", "no");
    requestMap.put("takingCurrentMedication", "no");
    requestMap.put("prescriptionAgreement", "yes");
    requestMap.put("everydayUseAgreement", "yes");
    requestMap.put("fourWeekGpAgreement", "yes");
    requestMap.put("threeMonthLimitAgreement", "yes");
    requestMap.put("sideEffectAgreement", "yes");

    requestObject.setAnswers(requestMap);

    var response = testRestTemplate.postForObject("http://localhost:" + port + "/api/consultation", requestObject,
        ConsultationResultResponse.class);

    assertThat(response.result()).isTrue();
  }

  @Test
  void testCreateNewConsultationQuestionCreatesNewQuestion() {
    var request = new ConsultationQuestionRequest("About You", "Have you had a migraine in the last 2 weeks?", "No", "hasMigraineRecently");

    var response = testRestTemplate.postForObject("http://localhost:" + port + "/api/consultation/questions", request,
        ConsultationQuestionResponse.class);

    assertThat(response.getQuestion()).isEqualTo("Have you had a migraine in the last 2 weeks?");
    assertThat(response.getCategory()).isEqualTo("About You");
    assertThat(response.getVariableName()).isEqualTo("hasMigraineRecently");
    assertThat(consultationQuestionRepository.findAll()).hasSize(12);
  }
}