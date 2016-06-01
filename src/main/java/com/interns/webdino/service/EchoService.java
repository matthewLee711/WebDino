package com.interns.webdino.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interns.webdino.model.EchoResponseModel;

@RestController
@RequestMapping("/echo")
public class EchoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoService.class);

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<EchoResponseModel> echo(
            HttpServletRequest req,
            @RequestParam(value="value", required=false) String value,
            @RequestParam(value="data", required=false) String data,
            @RequestBody String body){

        EchoResponseModel response;

        if (value == null && data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        LOGGER.info("value param was %s", value);
        LOGGER.info("data param was %s", data);

        if(value != null){
            response = new EchoResponseModel(value);
        } else {
            response = new EchoResponseModel(data);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}