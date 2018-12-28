package design.mode.dm.structural.flyweightp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 17:45
 */
public class PhoneConfiguration {

    private Apple phone;

    public PhoneConfiguration(Apple phone) {
        this.phone = phone;
    }

    private String cpu;
    private String color;
    private String resolutionRatio;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setResolutionRatio(String resolutionRatio) {
        this.resolutionRatio = resolutionRatio;
    }

    @Override
    public String toString() {
        return "PhoneConfiguration{" +
                "phone=" + phone +
                ", cpu='" + cpu + '\'' +
                ", color='" + color + '\'' +
                ", resolutionRatio='" + resolutionRatio + '\'' +
                '}';
    }
}
