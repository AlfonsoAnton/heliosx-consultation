package com.heliosxconsultation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.heliosxconsultation.data.entities.ConsultationQuestion;
import com.heliosxconsultation.data.repository.ConsultationQuestionRepository;
import com.heliosxconsultation.web.request.ConsultationQuestionRequest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsultationQuestionServiceTest {

  @Mock
  ConsultationQuestionRepository consultationQuestionRepository;

  @InjectMocks
  ConsultationQuestionService consultationQuestionService;

  @Test
  void testFindAllConsultationQuestions() {
    //given
    doReturn(List.of(new ConsultationQuestion(), new ConsultationQuestion())).when(consultationQuestionRepository)
        .findAll();
    //when
    var questionsFetched = consultationQuestionService.findAllConsultationQuestions();
    //then
    verify(consultationQuestionRepository).findAll();
    assertThat(questionsFetched).hasSize(2);
  }

  @Test
  void testCreateNewQuestion() {
    //given
    doReturn(new ConsultationQuestion()).when(consultationQuestionRepository).save(any(
        ConsultationQuestion.class));

    var question = "Is the sky blue?";
    var category = "About You";
    var answerForPrescription = "Yes";
    var variableName = "isSkyBlue";

    var testRequest = new ConsultationQuestionRequest(category, question, answerForPrescription, variableName);

    var testEntity = new ConsultationQuestion();
    testEntity.setQuestion(question);
    testEntity.setCategory(category);
    testEntity.setAnswerForPrescription(answerForPrescription);
    testEntity.setVariableName(variableName);
    
    //when
    consultationQuestionService.createNewQuestion(testRequest);

    //then
    verify(consultationQuestionRepository).save(eq(testEntity));
  }
}