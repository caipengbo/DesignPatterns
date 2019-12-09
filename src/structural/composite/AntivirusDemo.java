package structural.composite;

import java.util.ArrayList;

/**
 * Title: 组合模式Demo（杀毒软件设计） Desc: 杀毒软件可以对文件夹和文件进行杀毒，文件又分为不同的文件类型，杀毒方法也不一样（树形结构）
 * Created by Myth on 12/09/2019 in VSCode
 */
// 文件和文件夹同等对待, 树形结构
//抽象文件类：抽象构件
abstract class AbstractFile {
	public abstract void add(AbstractFile file);
	public abstract void remove(AbstractFile file);
	public abstract AbstractFile getChild(int i);
	public abstract void killVirus();
}
 
//图像文件类：叶子构件
class ImageFile extends AbstractFile {
	private String name;
	
	public ImageFile(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file) {
	   System.out.println("Do not Support this method!!！");
	}
	
	public void remove(AbstractFile file) {
		System.out.println("Do not Support this method!!！");
	}
	
	public AbstractFile getChild(int i) {
		System.out.println("Do not Support this method!!！");
		return null;
	}
	
	public void killVirus() {
		//模拟杀毒
		System.out.println("----Scan Image file: '" + name + "', killed virus----");
	}
}
 
//文本文件类：叶子构件
class TextFile extends AbstractFile {
	private String name;
	
	public TextFile(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file) {
	   System.out.println("Do not Support this method!!！");
	}
	
	public void remove(AbstractFile file) {
		System.out.println("Do not Support this method!!！");
	}
	
	public AbstractFile getChild(int i) {
		System.out.println("Do not Support this method!!！");
		return null;
	}
	
	public void killVirus() {
		//模拟杀毒
		System.out.println("----Scan Text file:'" + name + "', killed virus----");
	}
}
//文件夹类：容器构件
class Folder extends AbstractFile {
	//定义集合fileList，用于存储AbstractFile类型的成员
	private ArrayList<AbstractFile> fileList=new ArrayList<AbstractFile>();
	private String name;
		
	public Folder(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file) {
	   fileList.add(file);	
	}
	
	public void remove(AbstractFile file) {
		fileList.remove(file);
	}
	
	public AbstractFile getChild(int i) {
		return (AbstractFile)fileList.get(i);
	}
	
	public void killVirus() {
		System.out.println("****Scan folder：" + name + ", killing virus****");  //模拟杀毒
		//递归调用成员构件的killVirus()方法
		for(Object obj : fileList) {
			((AbstractFile)obj).killVirus();
        }
        System.out.println("****" + name + "'folder killed virus****");  //模拟杀毒
	}
}

public class AntivirusDemo {
    public static void main(String[] args) {
        AbstractFile file1,file2,file3,file4,folder1,folder2,folder3,folder4;
		
		folder1 = new Folder("MyFolder1");
		folder2 = new Folder("MyImage");
		folder3 = new Folder("MyText");
		folder4 = new Folder("MyVideo");
		
		file1 = new ImageFile("Picture1.jpg");
		file2 = new ImageFile("Picture2.gif");
		file3 = new TextFile("Text1.txt");
		file4 = new TextFile("Documents.doc");
 
		folder2.add(file1);
		folder2.add(file2);
		folder3.add(file3);
		folder3.add(file4);
		folder1.add(folder2);
		folder1.add(folder3);
		folder1.add(folder4);
		
		folder1.killVirus();
    }
}