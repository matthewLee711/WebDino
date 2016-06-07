package com.interns.webdino.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interns.webdino.model.EchoResponseModel;

@RestController
@RequestMapping("/echo")
public class EchoService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoService.class);

    // make two methods, one for get and one for post
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<EchoResponseModel> echoGet(
            HttpServletRequest req,
            @RequestParam(name = "value", required = false) String value) {

        EchoResponseModel model = new EchoResponseModel(value);

        ResponseEntity<EchoResponseModel> result = ResponseEntity.ok(model);

        return result;
    }

}