package structural.bridge;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Title: 桥接模式
 * Desc:  跨平台图像浏览系统
 * Created by Myth-Lab on 12/4/2019
 */
/*
Sunny软件公司欲开发一个跨平台图像浏览系统，要求该系统能够显示BMP、JPG、GIF、PNG等多种格式的文件，
并且能够在Windows、Linux、Unix等多个操作系统上运行。
系统首先将各种格式的文件解析为像素矩阵(Matrix)，然后将像素矩阵显示在屏幕上，在不同的操作系统中可以调用不同的绘制函数来绘制像素矩阵。
系统需具有较好的扩展性以支持新的文件格式和操作系统。（使用xml进行配置）
 */
// 多继承思路：Image， 多个FormatImage继承Image，PlatformImpl继承各个FormatImage，类爆炸
// 桥接模式：将文件格式和操作系统
//像素矩阵类：辅助类，各种格式的文件最终都被转化为像素矩阵，不同的操作系统提供不同的方式显示像素矩阵
class Matrix {
    //此处代码省略
}

//抽象图像类：抽象类
abstract class Image {
    protected PlatformInterface imp;  // 桥接
    // protected OtherDimImpl   // 两个以上的维度，再加一个桥接

    public void setImageImp(PlatformInterface imp) {
        this.imp = imp;
    }

    public abstract void parseFile(String fileName);
}
// 第一个维度
//JPG格式图像：扩充抽象类
class JPGImage extends Image {
    public void parseFile(String fileName) {
        // Parse JPG file
        Matrix m = new Matrix();
        imp.doPaint(m);  // 调用实现接口的方法
        System.out.println(fileName + ", JPG format.");
    }
}

//PNG格式图像：扩充抽象类
class PNGImage extends Image {
    public void parseFile(String fileName) {
        // Parse PNG file
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + ", PNG format.");
    }
}

//BMP格式图像：扩充抽象类
class BMPImage extends Image {
    public void parseFile(String fileName) {
        // Parse BMP file
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + ", BMP format.");
    }
}

//GIF格式图像：扩充抽象类
class GIFImage extends Image {
    public void parseFile(String fileName) {
        // Parse GIF file
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + ", GIF format.");
    }
}
// 第二个维度
//抽象操作系统实现类：实现类接口
interface PlatformInterface {
    public void doPaint(Matrix m);  //显示像素矩阵m
}
//Windows操作系统实现类：具体实现类
class WindowsImp implements PlatformInterface {
    public void doPaint(Matrix m) {
        //调用 Windows系统的绘制函数绘制像素矩阵
        System.out.print("Use Windows system call paint image!");
    }
}

//Linux操作系统实现类：具体实现类
class LinuxImp implements PlatformInterface {
    public void doPaint(Matrix m) {
        //调用Linux系统的绘制函数绘制像素矩阵
        System.out.print("Use Linux system call paint image!");
    }
}

//Unix操作系统实现类：具体实现类
class UnixImp implements PlatformInterface {
    public void doPaint(Matrix m) {
        //调用Unix系统的绘制函数绘制像素矩阵
        System.out.print("Use Unix system call paint image!");
    }
}
// 解析xml文件
class XMLUtil {
    //该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getBean(String args) {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("structural/bridge/config.xml"));
            NodeList nl=null;
            Node classNode=null;
            String cName = null;
            nl = doc.getElementsByTagName("className");

            if(args.equals("image")) {
                //获取第一个包含类名的节点，即扩充抽象类
                classNode=nl.item(0).getFirstChild();

            }
            else if(args.equals("os")) {
                //获取第二个包含类名的节点，即具体实现类
                classNode = nl.item(1).getFirstChild();
            }
            assert classNode != null;
            cName = classNode.getNodeValue();
            //通过类名生成实例对象并将其返回
            Class c = Class.forName(cName);
            return c.getDeclaredConstructor().newInstance();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
public class BridgeDemo {
    public static void main(String[] args) {
        Image image;
        PlatformInterface imp;
        image = (Image)XMLUtil.getBean("image");
        imp = (PlatformInterface)XMLUtil.getBean("os");
        assert image != null;
        image.setImageImp(imp);
        image.parseFile("test");
    }
}
