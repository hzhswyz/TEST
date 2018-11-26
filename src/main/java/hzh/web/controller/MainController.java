package hzh.web.controller;


import hzh.withpython.Receiveimg;
import hzh.withpython.SendImgInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @RequestMapping("/receive")
    public String receiveimg()  {
        try {
            Receiveimg.receiveimg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("testparameterresolver")
    public String testparameterresolver(HttpSession session, String name) throws Exception {
        logger.debug(name);
        logger.debug(System.getProperty("java.ext.dirs"));
        logger.debug(System.getProperty("java.class.path"));
        Class typeLoaded = Class.forName("java.lang.String");
        logger.debug(typeLoaded.getClassLoader());
        if(true)
        throw new Exception("");
        return "testparameterresolver";
    }

}
