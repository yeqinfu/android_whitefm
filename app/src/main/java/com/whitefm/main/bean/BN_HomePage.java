package com.whitefm.main.bean;

/**
 * Created by yeqinfu on 8/24/16.
 */
public class BN_HomePage {

    /**
     * id : string
     * name_music : string
     * id_dj : string
     * id_class : string
     * intro : string
     * content : string
     * music_address : string
     * pic_address : string
     */

    private String id;
    private String name_music;
    private String id_dj;
    private String id_class;
    private String intro;
    private String content;
    private String music_address;
    private String pic_address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_music() {
        return name_music;
    }

    public void setName_music(String name_music) {
        this.name_music = name_music;
    }

    public String getId_dj() {
        return id_dj;
    }

    public void setId_dj(String id_dj) {
        this.id_dj = id_dj;
    }

    public String getId_class() {
        return id_class;
    }

    public void setId_class(String id_class) {
        this.id_class = id_class;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMusic_address() {
        return music_address;
    }

    public void setMusic_address(String music_address) {
        this.music_address = music_address;
    }

    public String getPic_address() {
        return pic_address;
    }

    public void setPic_address(String pic_address) {
        this.pic_address = pic_address;
    }
}
