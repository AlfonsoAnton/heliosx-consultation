package com.heliosxconsultation.web.request;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultationSubmissionRequest {
  Map<String, String> answers;

  public ConsultationSubmissionRequest(){
   //default no-arg
  }
}
