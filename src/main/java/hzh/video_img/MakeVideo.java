package hzh.video_img;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.jim2mov.core.DefaultMovieInfoProvider;
import org.jim2mov.core.ImageProvider;
import org.jim2mov.core.Jim2Mov;
import org.jim2mov.core.MovieInfoProvider;
import org.jim2mov.core.MovieSaveException;
import org.jim2mov.utils.MovieUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * 图片与视频转换工具类
 *
 */
public class MakeVideo {

    /**
     * 将图片转换成视频
     * @param jpgDirPath jpg图片文件夹绝对路径
     * @param aviFileName 生成的avi视频文件名
     * @param fps 每秒帧数
     * @param mWidth 视频的宽度
     * @param mHeight 视频的高度
     */
    public static void convertPicToAvi(String jpgDirPath, String aviFileName, int fps, int mWidth, int mHeight) {
        // jpgs放置jpg图片,图片文件名为(0.jpg,2.jpg...)
        final File[] jpgs = new File(jpgDirPath).listFiles();
        if(jpgs==null || jpgs.length==0){
            return;
        }
        // 对文件名进行排序(文件名中的数字越小,生成视频的帧数越靠前)
        Arrays.sort(jpgs, new Comparator<File>() {
            public int compare(File file1, File file2) {
                String numberName1 = file1.getName().replace(".jpg", "");
                String numberName2 = file2.getName().replace(".jpg", "");
                return new Integer(numberName1) - new Integer(numberName2);
            }
        });
        // 生成视频的名称
        DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider(aviFileName);
        // 设置每秒帧数
        dmip.setFPS(fps>0?fps:30); // 如果未设置，默认为30
        // 设置总帧数
        dmip.setNumberOfFrames(jpgs.length);
        // 设置视频宽和高（最好与图片宽高保持一直）
        dmip.setMWidth(mWidth>0?mWidth:1280); // 如果未设置，默认为1280
        dmip.setMHeight(mHeight>0?mHeight:760); // 如果未设置，默认为760
        try {
            new Jim2Mov(new ImageProvider() {
                public byte[] getImage(int frame) {
                    try {
                        // 设置压缩比
                        return MovieUtils.convertImageToJPEG((jpgs[frame]), 1.0f);
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    return null;
                }
            }, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_MJPEG);
        } catch (MovieSaveException e) {
            System.err.println(e);
        }

        System.out.println("create avi success.");
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        // jpg文件夹路径
        ClassPathResource classPathResource = new ClassPathResource("videoimg");
        String savePath = null;
        try {
            savePath = classPathResource.getFile().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(savePath);
        String jpgDirPath = savePath;
        // 生成的avi视频文件名（生成路径为本工程）
        String aviFileName = "test.avi";
        int fps = 30; // 每秒播放的帧数
        int mWidth = 1280; // 视频的宽度
        int mHeight = 760; // 视频的高度
        MakeVideo.convertPicToAvi(jpgDirPath, aviFileName, fps, mWidth, mHeight);
    }

}