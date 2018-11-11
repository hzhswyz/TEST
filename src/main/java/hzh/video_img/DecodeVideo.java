package hzh.video_img;

import org.apache.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import com.googlecode.javacv.cpp.opencv_highgui;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import java.io.IOException;


public class DecodeVideo {

private static Logger logger = Logger.getLogger(DecodeVideo.class);

    public static void main(String[] args) {
        System.loadLibrary("opencv_ffmpeg341_64");
        // 加载本地的OpenCV库，这样就可以用它来调用Java API
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        run2();
    }

    public static void run2() {
        // 读取视频文件
        Resource resource = new ClassPathResource("video/rotate_19.mp4");
        ClassPathResource classPathResource = new ClassPathResource("videoimg");
        String savePath = null;
        try {
            savePath = classPathResource.getFile().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = "";
        try {
            filePath = resource.getFile().getAbsolutePath();
            logger.debug(filePath);
            logger.debug(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        VideoCapture cap = new VideoCapture(filePath);
        System.out.println(cap.isOpened());
        // 判断视频是否打开
        if (cap.isOpened()) {
            // 总帧数
            double frameCount = cap.get(opencv_highgui.CV_CAP_PROP_FRAME_COUNT);
            System.out.println("视频总帧数:" + frameCount);
            // 帧率
            double fps = cap.get(opencv_highgui.CV_CAP_PROP_FPS);
            System.out.println("视频帧率" + fps);
            // 时间长度
            double len = frameCount / fps;
            System.out.println("视频总时长:" + len);
            Double d_s = frameCount;
            System.out.println("保存截图数量:"+d_s.intValue());
            Mat frame = new Mat();
            for (int i = 0; i < d_s.intValue(); i++) {
                // 设置视频的位置(单位:毫秒)
                cap.set(opencv_highgui.CV_CAP_PROP_POS_MSEC, i *(int)(1000/fps));
                // 读取下一帧画面
                if (cap.read(frame)) {
                    System.out.println("正在保存第"+i+"帧");
                    // 保存画面到本地目录
                    Imgcodecs.imwrite(savePath+"\\" + i + ".jpg", frame);
                }
            }
            // 关闭视频文件
            cap.release();
        }
    }
}
