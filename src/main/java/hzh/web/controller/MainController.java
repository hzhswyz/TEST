package hzh.web.controller;


import hzh.withpython.SendImgInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Main")
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/sendimg{imageName:.+}")
    public String sendimg(@PathVariable("imageName") String imageName)  {
        if(!imageName.equals("")){
            try {
                SendImgInfo.sendImgInfoToPython(imageName);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return "index";
        }
        return "index";
    }

    @RequestMapping("testparameterresolver")
    public String testparameterresolver(String name){
        logger.debug(name);
        return "testparameterresolver";
    }
}
