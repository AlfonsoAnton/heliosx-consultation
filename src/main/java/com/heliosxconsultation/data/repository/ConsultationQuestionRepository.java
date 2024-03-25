package com.heliosxconsultation.data.repository;

import com.heliosxconsultation.data.entities.ConsultationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationQuestionRepository extends JpaRepository<ConsultationQuestion, Long> {

  void deleteByVariableName(String variableName);
}
