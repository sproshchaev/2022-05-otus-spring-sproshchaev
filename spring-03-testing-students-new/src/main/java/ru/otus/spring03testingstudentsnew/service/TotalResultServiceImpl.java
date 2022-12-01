package ru.otus.spring03testingstudentsnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring03testingstudentsnew.pojo.Result;


@Component
public class TotalResultServiceImpl implements TotalResultService{
    private final ResultService resultService;
    private final IOService ioService;

    @Autowired
    public TotalResultServiceImpl(ResultService resultService,
                                  IOService ioService) {
        this.resultService = resultService;
        this.ioService = ioService;
    }

    @Override
    public void doPrintResult(Result result) {
        ioService.outputString("Your result: " + resultService.getResult(result));
    }

}
