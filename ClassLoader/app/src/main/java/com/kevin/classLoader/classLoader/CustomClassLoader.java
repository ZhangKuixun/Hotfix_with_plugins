package com.kevin.classLoader.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import dalvik.system.DexClassLoader;

/**
 * 模拟如何去定义自己的一个ClassLoader。
 */
public class CustomClassLoader extends DexClassLoader {
    public CustomClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }

    //要以何种策略加载class文件。我们这里知识模拟加载,没有任何策略
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classData = getClassData(name);

        //假如我正真的拿到了字节码

        if (classData != null) {

            //将字节数组转化成class字节码
            return defineClass(name, classData, 0, classData.length);
        } else {

            //没有找到这个类
            throw new ClassNotFoundException("没有找到这个类");
        }
    }

    //加载class字节码
    private byte[] getClassData(String name) {
        try {
            FileInputStream inputStream = new FileInputStream(name);
            //由于我们读取的是字节，用一个字节包装流数组，对他进行一个包装
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;//每次读取缓存大小
            byte[] buffer = new byte[bufferSize];//缓存字节数组
            int bytesNumRead = -1;//读取的索引
            while ((bytesNumRead = inputStream.read(buffer)) != -1) {//先获取每次读了多少，如果不等于-1，表明文件没有读完，就在while中读，读到buffer缓存数组中
                baos.write(buffer, 0, bytesNumRead);//再把buffer写入到字节输出流中。参数1-缓存字节码数组，参数2-起始位置，参数3-结尾位置
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
