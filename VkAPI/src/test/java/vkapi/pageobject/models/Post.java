package vkapi.pageobject.models;

import vkapi.pageobject.utills.RandomTextMaker;

public class Post {
    private int postId;
    private String text;
    private int pictureId;
    private String commentText;


    public Post(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public String getText() {
        return text;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
