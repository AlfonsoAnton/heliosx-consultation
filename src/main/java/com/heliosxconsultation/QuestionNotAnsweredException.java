package com.heliosxconsultation;

public class QuestionNotAnsweredException extends RuntimeException{

  public QuestionNotAnsweredException() {
    super("Some or all of the questions were not answered.");
  }
}
