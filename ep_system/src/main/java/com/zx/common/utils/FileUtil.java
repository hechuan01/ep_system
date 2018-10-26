package com.zx.common.utils;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by wang on 2017/3/18.
 */
public class FileUtil implements ServletContextAware {
    private ServletContext servletContext;

    /**
     * Method MultipartFile 保存
     *
     * @param file     保存文件
     * @param RootPath 项目根目录
     * @savePath 相对路径
     */

    public static FileReturn saveMultipartFile(MultipartFile file, String RootPath, String savePath) {

        String fileName = "";//文件名
        String filePath = "";//文件存储真实路径

        if (file != null) {
            // 判断文件是否为空
            if (!file.isEmpty()) {
                try {

                    fileName = file.getOriginalFilename();//文件名
                    String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);//文件拓展名
                    // 文件保存路径
                    filePath = RootPath + savePath.trim();//文件保存路径

                    File dirfile = new File(filePath);//保存路径对象
                    if (!dirfile.exists()) {///如果文件夹不存在则创建新的文件夹
                        dirfile.mkdirs();
                    }
                    String newFileName = UUID.randomUUID() + "." + prefix;//新生成的UUID文件名，防止上传同名文件覆盖问题
                    filePath = filePath + "/" + newFileName;//最终保存文件地址加名称
                    // 转存文件
                    File finaleFile = new File(filePath);//需要保存的文件
                    filePath = finaleFile.getAbsolutePath();//文件中重新获路径，防止路径不规范现象
                    file.transferTo(finaleFile);//保存文件

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return new FileReturn(fileName, filePath.replace(RootPath, ""));
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String RootPath, String fileName) {
        File file = new File(RootPath + "/" + fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /*
    * 根据文件地址和文件名下载文件
    * */
    public static void fileDownload(String RootPath, String filePath, String fileName, HttpServletResponse response) {
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        ServletOutputStream out;
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
        File file = new File(RootPath + filePath);

        try {
            FileInputStream inputStream = new FileInputStream(file);

            //3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1) {
                b = inputStream.read(buffer);
                //4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
