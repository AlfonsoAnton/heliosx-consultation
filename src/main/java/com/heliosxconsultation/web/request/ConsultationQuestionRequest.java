package com.heliosxconsultation.web.request;

public record ConsultationQuestionRequest(String category, String question, String answerForPrescription,
                                          String variableName) {

}
