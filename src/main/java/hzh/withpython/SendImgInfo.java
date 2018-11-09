package hzh.withpython;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.Socket;

public class SendImgInfo {

    private static Logger logger = Logger.getLogger(SendImgInfo.class);

    public static void sendImgInfoToPython(String imageName)throws Exception{

        //logger.debug(this.getClass().getResource("../../img/PartB_00000.jpg").getFile());
        /*try {
            File file = new File("../../img/PartB_00000.jpg");
            String content = ImgToJson.getimgstr(file);
            logger.debug(file.getAbsolutePath());
            logger.debug(content);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        //直接使用new File("")会将文件定位到Tomcat服务器的bin目录下，所以使用ClassPathResource("")或者Class.getResource("")方法获取文件
        //创建ClassPathResource，指定资源位置
        Resource resource = new ClassPathResource("img/"+imageName);
        logger.debug(resource.getFile().getPath());
        //通过ImgToJson.getimgison()将img图片转为字节数组，再将字节数组转为String字符串
        String imgstr = ImgToJson.getimgstr(resource.getFile());
        logger.debug("imgName:"+resource.getFilename());
        logger.debug("imgSize:"+resource.getFile().length());
        logger.debug("imgByteStr:"+imgstr);
        ImgInfoBean imgInfoBean = new ImgInfoBean();
        imgInfoBean.setFilename(resource.getFilename());
        imgInfoBean.setFilesize(resource.getFile().length());
        imgInfoBean.setContent(imgstr);
        //将java对象转换为json对象
        JSONObject json = JSONObject.fromObject(imgInfoBean);
        //将json对象转换为字符串
        String jsonStr = json.toString();
        logger.debug(jsonStr);
        //创建客户端Socket，指定服务器地址和端口
        Socket socket =new Socket("192.168.0.5",6666);
        //获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
        pw.write(jsonStr);
        pw.flush();
        socket.shutdownOutput();
        //获取输入流，并读取服务器端的响应信息
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info;
        while((info=br.readLine())!=null){
            System.out.println("服务器放回的Json："+info);
        }
        //关闭资源
        br.close();
        is.close();
        pw.close();
        os.close();
        socket.close();
    }
}
