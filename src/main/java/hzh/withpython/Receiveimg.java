package hzh.withpython;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class Receiveimg {
    private static Logger logger = Logger.getLogger(Receiveimg.class);
    public  static void receiveimg()throws Exception{
        //创建客户端Socket，指定服务器地址和端口
        Socket socket =new Socket("192.168.3.108",6666);
        //获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
        pw.write('w');
        pw.flush();
        socket.shutdownOutput();
        //获取输入流，并读取服务器端的响应信息
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ByteArrayOutputStream byteArrayOutputStream ;
        String info;
        byte[] bytes = new byte[1024];
        byte[] data;
        while((info=br.readLine()) != null){
            String beanStr = info;
            System.out.println("服务器返回的Json字符串："+beanStr);
            JSONObject json = JSONObject.fromObject(beanStr);
            ImgInfo imgInfo = (ImgInfo) JSONObject.toBean(json,ImgInfo.class);
            logger.debug("图片宽度:"+imgInfo.getWidth()+" 图片高度:"+imgInfo.getHeight()+" 图片字节数大小:"+imgInfo.getLength());
            byteArrayOutputStream = new ByteArrayOutputStream(imgInfo.getLength());
            int len;
            //开始读取文件
            while((len=is.read(bytes))!= -1){
                //从bytes的第0位置开始，读取至第len位置，结果写入byteArrayOutputStream
                byteArrayOutputStream.write(bytes,0,len);
            }
            //将输出流转为字节数组
            data = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            logger.debug("接收到的图片大小："+data.length);
            System.out.println();
        }
        //关闭资源
        br.close();
        is.close();
        pw.close();
        os.close();
        socket.close();
    }
}
