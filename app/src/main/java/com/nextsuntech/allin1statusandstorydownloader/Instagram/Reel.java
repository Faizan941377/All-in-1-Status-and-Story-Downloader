package com.nextsuntech.allin1statusandstorydownloader.Instagram;

import com.nextsuntech.allin1statusandstorydownloader.Model.InstagramDataModel;

public class Reel {
    private InstagramDataModel shortcode_media;

    public Reel(InstagramDataModel shortcode_media) {
        this.shortcode_media = shortcode_media;
    }

    public InstagramDataModel getShortcode_media() {
        return shortcode_media;
    }

    public void setShortcode_media(InstagramDataModel shortcode_media) {
        this.shortcode_media = shortcode_media;
    }
}
