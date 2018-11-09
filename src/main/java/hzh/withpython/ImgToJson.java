package hzh.withpython;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class ImgToJson {


    public static String getimgstr(File e){
        String json = byteToString(loadImage(e));
        return json;
    }

    /**
     * 将图片转换为字节数组
     * @param file
     * @return
     */
    private static byte[] loadImage(File file){
        //用于返回的字节数组
        byte[] data=null;
        //打开文件输入流
        FileInputStream fin=null;
        //打开字节输出流
        ByteArrayOutputStream bout=null;
        try{
            //文件输入流获取对应文件
            fin=new FileInputStream(file);
            //输出流定义缓冲区大小
            bout=new ByteArrayOutputStream((int)file.length());
            //定义字节数组，用于读取文件流
            byte[] buffer=new byte[1024];
            //用于表示读取的位置
            int len=-1;
            //开始读取文件
            while((len=fin.read(buffer))!=-1){
                //从buffer的第0位置开始，读取至第len位置，结果写入bout
                bout.write(buffer,0,len);
            }
            //将输出流转为字节数组
            data=bout.toByteArray();
            //关闭输入输出流
            fin.close();
            bout.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    /**
     * 把字节数组转化为字符串----"utf-8"
     * @param data
     * @return
     */
    private static String byteToString(byte[] data){
        String dataString=null;
        try{
            //将字节数组转为字符串，编码格式为utf-8
            dataString=new String(data,"utf-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        return dataString;
    }
}
