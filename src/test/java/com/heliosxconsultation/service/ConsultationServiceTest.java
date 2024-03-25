package com.heliosxconsultation.service;

import com.heliosxconsultation.QuestionNotAnsweredException;
import com.heliosxconsultation.data.dto.ConsultationQuestionDto;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsultationServiceTest {

  private static final ConsultationQuestionDto question1 = new ConsultationQuestionDto("About You", "Is the sky blue?", "yes", "isSkyBlue");
  private static final ConsultationQuestionDto question2 = new ConsultationQuestionDto("Health", "Does the sun set in the West?", "yes", "doesSunSetInWest");
  private static final ConsultationQuestionDto question3 = new ConsultationQuestionDto("Medical", "Can pigs fly?", "no", "canPigsFly");

  @Mock
  ConsultationQuestionService consultationQuestionService;

  @InjectMocks
  ConsultationService consultationService;

  @Test
  void testDeterminePrescriptionStatusThrowsExceptionWhenMissingAnswers() {
    //given
    Mockito.doReturn(List.of(question1, question2, question3)).when(consultationQuestionService).findAllConsultationQuestions();
    var submissionAnswers = Map.of("isSkyBlue", "yes", "doesSunSetInWest", "yest");

    //when & then
    Assertions.assertThatCode(() -> consultationService.determinePrescriptionStatus(submissionAnswers)).isInstanceOf(
        QuestionNotAnsweredException.class);
  }

  @Test
  void testDeterminePrescriptionStatusThrowsExceptionWhenSubmissionContainsUnrecognizedQuestion() {
    //given
    Mockito.doReturn(List.of(question1, question2, question3)).when(consultationQuestionService).findAllConsultationQuestions();
    var submissionAnswers = Map.of("isSkyBlue", "yes", "doesSunSetInWest", "yest", "iAmInvalidQuestion", "yes");

    //when & then
    Assertions.assertThatCode(() -> consultationService.determinePrescriptionStatus(submissionAnswers)).isInstanceOf(
        QuestionNotAnsweredException.class);
  }
}