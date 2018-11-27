package hzh.web.controller;

import hzh.web.bean.ConnectionResult;
import hzh.web.httpbean.ConnectResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Skating")
public class SkatingController {

    private static Logger logger = Logger.getLogger(SkatingController.class);

    @RequestMapping("/requestmodel")
    public ResponseEntity<ConnectResponse> requestmodel(){
        ConnectResponse connectResponse = new ConnectResponse();
        connectResponse.setStatus(ConnectionResult.Success);
        connectResponse.setSuccess(true);
        connectResponse.setMessagequeue(new ConnectResponse.Messagequeue("failover://tcp://120.79.16.31:61616","hzh","Hzh1997","queue","Real time detection origin video _ 0"));
        return new ResponseEntity<ConnectResponse>(connectResponse, HttpStatus.OK);
    }
}
