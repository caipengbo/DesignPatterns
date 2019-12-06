package structural.adapter;

/**
 * Title: 适配器模式（包装器）
 * Desc: 将一个接口转换成客户希望的另一个接口，使接口不兼容的那些类可以一起工作，其别名为包装器(Wrapper)
 * 主要角色：已适配者   —— 适配器 —— 适配目标
 * Created by Myth-Lab on 11/28/2019
 */
// 以下是一个很糟糕的例子
// 需求: 让 AudioPlayer可以播放
class AudioPlayerOld implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        //内置支持
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
    }
}

// 添加一个适配器（注意是实现的旧接口），适配器内部调用 另一个接口
// 适配器继承或依赖已有的对象，实现想要的目标接口。
class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;  // 调用另一个接口

    public MediaAdapter(String audioType){
        // 依赖已有对象
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }
    }
    // 实现想要的接口
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
// 在AudioPlayer使用Adapter
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;  // 添加Adapter依赖
    // 有动机地修改一个正常运行的系统的接口(没有做到开闭原则)
    @Override
    public void play(String audioType, String fileName) {
        //播放 mp3 音乐文件的内置支持（一开始就有的代码）
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持（额外添加的）
        else if(audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else{
            System.out.println("Invalid media. "+ audioType + " format not supported");
        }
    }
}
public class AdapterDemo {

}
