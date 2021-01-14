package JVM;

import java.io.FileNotFoundException;

/**
 * 用户自定义的类加载器
 */
public class CustomClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        try {
            byte[] result = getClassFromCustomPath(name);
            if(result == null){
                throw new FileNotFoundException();
            }else {
                return defineClass(name,result,0,result.length);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    /**
     * 从自定义路径中加载指定类
     * 如果指定路径的字节码文件进行了加密，则需要在该方法中进行解密操作
     */
    private byte[] getClassFromCustomPath(String name){
        return null;
    }

}
