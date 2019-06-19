package com.cloud.web.myclassloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author: jack
 * @create: 2019-06-20
 * @description: 自定义类加载器加载指定资源
 **/
public class MyClassLoader extends ClassLoader {

    private String classpath;

    public MyClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getClassFile(name);
        byte[] classByte = null;
        try {
            classByte = getClassBytes(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //利用自身的加载器加载类
        Class retClass = defineClass(null, classByte, 0, classByte.length);
        if (retClass != null) {
            System.out.println("由我加载");
            return retClass;
        }
        //System.out.println("非我加载");
        //在classPath中找不到类文件，委托给父加载器加载,父类会返回null，因为可加载的话在
        //委派的过程中就已经被加载了
        return super.findClass(name);
    }

    /**
     * @Date: 2019-06-20
     * @Param: fileName
     * @return: byte[]
     * @Description: 获取指定类文件的字节数组
     */
    private byte[] getClassBytes(String name) throws IOException {
        FileInputStream fileInput = new FileInputStream(name);
        FileChannel channel = fileInput.getChannel();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        WritableByteChannel byteChannel = Channels.newChannel(output);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int flag;
            while ((flag = channel.read(buffer)) != -1) {
                if (flag == 0) {
                    break;
                }
                //将buffer写入byteChannel
                buffer.flip();
                byteChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            System.out.println("can't read!");
            throw e;
        }
        fileInput.close();
        channel.close();
        byteChannel.close();
        return output.toByteArray();
    }

    /***
     * 获取当前操作系统下的类文件合法路径
     * @param name
     * @return 合法的路径文件名
     */
    private String getClassFile(String name) {
        //利用StringBuilder将包形式的类名转化为Unix形式的路径
        StringBuilder sb = new StringBuilder(classpath);
        sb.append("/")
                .append(name.replace('.', '/'))
                .append(".class");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("E:/MyClassLoaderTest");
        try {
            myClassLoader.loadClass("java.io.InputStream");
            myClassLoader.loadClass("ClassLoaderTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
