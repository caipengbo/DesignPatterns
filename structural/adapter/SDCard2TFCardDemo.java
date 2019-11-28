package structural.adapter;

/**
 * Title:  笔记本通过读卡去读取TF卡（特别好的例子）
 * Desc: 笔记本电脑内置是可以读取SD卡的
 * Created by Myth-Lab on 11/28/2019
 */
// *****************************Computer已经存在读取SD卡的功能***********************************************************
interface SDCard {
    //读取SD卡方法
    void read();
}
// 金士顿SD卡
class KingstonSDCard implements SDCard {
    @Override
    public void read() {
        System.out.println("Read Kingston SD Card");
    }
}
// 计算机（提供读SD卡的接口）
interface Computer {
    void readSD(SDCard sdCard);
}
class ThinkpadComputer implements Computer {
    @Override
    public void readSD(SDCard sdCard) {
        if(sdCard == null)throw new NullPointerException("No SD Card!!!");
        sdCard.read();
    }
}
// ***************************让计算机通过原有的readSD接口可以读取TF卡*************************************************
interface TFCard {
    //读取TF卡方法
    void read();
}
// 闪迪 TF卡
class SanDisk implements TFCard {
    @Override
    public void read() {
        System.out.println("Read SanDisk TF Card");
    }
}
// 添加适配器（读卡器，包装器）（适配器实现适配者，在内部调用要适配的目标） 桥梁作用
class TFAdpter implements SDCard {
    TFCard tfCard;

    TFAdpter(TFCard tfCard) {
        this.tfCard = tfCard;
    }
    @Override
    public void read() {
        tfCard.read();
    }
}
public class SDCard2TFCardDemo {
    // 计算机读 SD 卡
    public static void computerReadSDCard() {
        Computer computer = new ThinkpadComputer();
        SDCard sdCard = new KingstonSDCard();
        computer.readSD(sdCard);
    }
    public static void computerReadTFCard() {
        Computer computer = new ThinkpadComputer();
        TFCard tfCard = new SanDisk();
        SDCard adpter = new TFAdpter(tfCard);
        computer.readSD(adpter);
    }
    public static void main(String[] args) {
        computerReadSDCard();
        computerReadTFCard();
    }
}
