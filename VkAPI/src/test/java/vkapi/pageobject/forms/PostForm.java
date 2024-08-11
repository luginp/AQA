package vkapi.pageobject.forms;

import framework.elements.Button;
import framework.elements.Image;
import framework.elements.Label;
import framework.utils.ReadValuesFromConfig;
import vkapi.pageobject.models.Post;
import vkapi.pageobject.utills.ElementWaits;

public class PostForm extends BaseForm {
    private String pictureLocator = "//a[contains(@href,'photo')]";

    private Button likeButton = new Button(getLocator() + "//div[@class='like_button_icon']", "Like Button");
    private Button nextCommentButton = new Button(getLocator() + "//a[@class='replies_next  replies_next_main']", "Next comment button");
    private Label replyLabel = new Label(getLocator() + "//div[@class='wall_reply_text']", "Reply label");
    private Label replyAuthorLabel = new Label(getLocator() + "//div[@class='reply_author']//a[@class='author']", "Reply author label");
    private Label postAuthorName = new Label(getLocator() + "//a[@class='author']", "Post author label");
    private Label post = new Label(getLocator(), "Post label");
    private Label postLabel = new Label(getLocator() + "//div[contains(@class, 'wall_post_text')]", "Post text label");
    private Image pictureDownloaded = new Image(getLocator() + pictureLocator, "Our picture");


    public PostForm(Post post) {
        super(String.format("//div[@id='post%s_%d']", userId, post.getPostId()), "Created post form");
    }

    private static final String userId = ReadValuesFromConfig.readValues("userId", ReadValuesFromConfig.testPropertiesFile);

    public  String getUserId() {
        return userId;
    }

    public void likeClick() {
        ElementWaits.explicitWaitForClickable(likeButton.getLocator());
        likeButton.click();
    }

    public boolean commentIsVisible(Post post) {
        nextCommentButton.click();
        return replyLabel.getText().contains(post.getCommentText()) && replyAuthorLabel.getAttribute("href").contains(getUserId());
    }

    public boolean postIsVisible() {
        return post.isVisible();
    }

    public boolean rightUserTextAdd(Post post) {
        return postLabel.getText().contains(post.getText()) &&
                postAuthorName.getAttribute("href").contains(getUserId());
    }

    public boolean rightUserAddedImage(Post post) {
        ElementWaits.explicitWaitForPresence(pictureLocator);
        return pictureDownloaded.getAttribute("href").contains(Integer.toString(post.getPictureId()));
    }

    public boolean postDeleted() {
        ElementWaits.explicitWaitForInvisibility(getLocator());
        return !post.isVisible();
    }
}
