package hzh.web.controller;

import hzh.web.httpbean.ConnectResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Skating")
public class SkatingController {

    private static Logger logger = Logger.getLogger(SkatingController.class);

    @RequestMapping("/requestmodel")
    public ResponseEntity<ConnectResponse> requestmodel(ModelMap modelMap){
        ConnectResponse connectResponse = new ConnectResponse();
        connectResponse.setStatus("1");
        connectResponse.setSuccess(false);
        modelMap.addAttribute("content",connectResponse);
        return new ResponseEntity<ConnectResponse>(connectResponse, HttpStatus.OK);
    }

}
