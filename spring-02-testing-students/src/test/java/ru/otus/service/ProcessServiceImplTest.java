package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Testing process ")
@ExtendWith(MockitoExtension.class)
class ProcessServiceImplTest {
    private final static int TOTAL_QUESTIONS_IN_TEST = 5;
    @Mock
    private WelcomeServiceImpl welcomeService;
    @Mock
    private QuestionServiceImpl questionService;
    @Mock
    private TotalResultServiceImpl totalResultService;
    @Mock
    private ResultServiceImpl resultService;
    private ProcessServiceImpl processService;

    @BeforeEach
    void setUp() {
        processService = new ProcessServiceImpl(TOTAL_QUESTIONS_IN_TEST, welcomeService, questionService,
                totalResultService, resultService);
    }

    @Test
    @DisplayName("returns the number of questions output")
    void shouldReturnNumberQuestionsInRunProcess() {
        assertThat(processService.runProcess()).isEqualTo(TOTAL_QUESTIONS_IN_TEST);
    }

}