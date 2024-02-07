
interface TV{
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int v);

    int getChannel();

    void setChannel(int ch);

}

class SmartTV implements TV{
    boolean powerOn=false;
    int volume=50;
    int channel=2;

    @Override
    public boolean isEnabled() {
        return powerOn;
    }

    @Override
    public void enable() {
        powerOn=true;
    }

    @Override
    public void disable() {
        powerOn=false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int v) {
        volume=v;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int ch) {
        this.channel=ch;
    }
    public void Youtube(YoutubeClass ytb){
        System.out.println("Youtube for SmartTV -> Is Called");
        ytb.runYoutube();
    }
}

class GeneralTV implements TV{
    boolean powerOn=false;
    int volume=30;
    int channel=2;

    @Override
    public boolean isEnabled() {
        return powerOn;
    }

    @Override
    public void enable() {
        powerOn=true;
    }

    @Override
    public void disable() {
        powerOn=false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int v) {
        volume=v;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int ch) {
        this.channel=ch;
    }
}


class Remote{
    protected TV tv;

    Remote(){tv=null;}
    Remote(TV tv){
        this.tv=tv;
    }
    public void togglePower() {
        if(tv.isEnabled()){
            tv.disable();
            System.out.println("Power turned off ");
        }
        else {
            tv.enable();
            System.out.println("Power turned on ");
        }
    }

    public void volumeUp() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()+5);
            System.out.println("Increasing volume +5 ");
        }else{
            System.out.println("Please Turn on tv first ");
        }
    }

    public void volumeDown() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()-5);
            System.out.println("Decreasing volume -5 -");
        }else{
            System.out.println("Please Turn on tv first ");
        }

    }

    public void channelUp() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getVolume()+1);
            System.out.println("Channel +1 ");
        }else{
            System.out.println("Please Turn on tv first ");
        }


    }

    public void channelDown() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getVolume()-1);
            System.out.println("Channel -1 ");
        }else{
            System.out.println("Please Turn on tv first ");
        }

    }
}

class SmartRemote extends Remote{

    SmartRemote(){

    }
    SmartRemote(TV tv){
        super(tv);
    }
    void showYoutube(YoutubeClass ytb){
        ((SmartTV)tv).Youtube(ytb);
    }
}

interface YoutubeClass{
    void runYoutube();
}
class FirstLoadYoutube implements YoutubeClass{

    @Override
    public void runYoutube() {
        System.out.println("Running Youtube ");
    }
}

class proxyLoadYoutube implements YoutubeClass{
    private FirstLoadYoutube firstLoadYoutube;
    @Override
    public void runYoutube() {
        if(firstLoadYoutube==null){
            firstLoadYoutube=new FirstLoadYoutube();
            System.out.println("Starting Youtube ");
        }
        firstLoadYoutube.runYoutube();
    }
}

public class labTask4 {
    public static void main(String[] args) {


        //GeneralTV

        GeneralTV gn=new GeneralTV();
        Remote remote= new Remote(gn);
        System.out.println("General Tv ");
        remote.togglePower();
        remote.volumeUp();
        remote.channelUp();
        remote.channelDown();
        remote.volumeDown();

        //SmartTV
        System.out.println();
        System.out.println("Smart Tv ");
        SmartTV s=new SmartTV();
        SmartRemote sremote= new SmartRemote(s);
        sremote.togglePower();
        sremote.volumeUp();
        sremote.channelUp();
        sremote.channelDown();
        sremote.volumeDown();

        System.out.println();
    
        YoutubeClass ytb=new proxyLoadYoutube();

        
        sremote.showYoutube(ytb);
        sremote.showYoutube(ytb);
    }

}