package vkapi.pageobject.utills;

import framework.utils.ApiUtils;
import framework.utils.ReadValuesFromConfig;
import vkapi.pageobject.models.Post;

public class VkApiUtils {
    private final String API_URL = "https://api.vk.com/method/%s&access_token=%s&v=%s";

    private final String USER_ID = ReadValuesFromConfig.readValues("userId", ReadValuesFromConfig.testPropertiesFile);
    private final String TOKEN = ReadValuesFromConfig.readValues("token", ReadValuesFromConfig.testPropertiesFile);
    private final String API_VERSION = ReadValuesFromConfig.readValues("apiVersion", ReadValuesFromConfig.testPropertiesFile);

    private final String wallPost = "wall.post?message=%s";
    private final String wallEdit = "wall.edit?post_id=%d&message=%s&attachments=photo%s_%d";
    private final String wallComment = "wall.createComment?post_id=%d&message=%s";
    private final String likesGet = "likes.getList?type=post&item_id=%d&extended=1";
    private final String wallDelete = "wall.delete?post_id=%d";
    private final String savesWallPhoto = "photos.saveWallPhoto?photo=%s&server=%d&hash=%s";
    private final String getUploadServer = "photos.getWallUploadServer?";

    private final String postId = "$.response.post_id";
    private final String uploadUrl = "$.response.upload_url";
    private final String userLikeId = "$.response.items";
    private final String photo = "$.photo";
    private final String server = "$.server";
    private final String hash = "$.hash";
    private final String photoIdText = "$.response[0].id";

    private String picture = ReadValuesFromConfig.readValues("picture", ReadValuesFromConfig.testPropertiesFile);


    private String getApiUrl(String method) {
        return String.format(API_URL, method, TOKEN, API_VERSION);
    }

    public void createPost(Post post) {
        ApiUtils.makePostApiRequest(getApiUrl(String.format(wallPost, post.getText())));
        int postIdInt = ApiUtils.parseForInt(postId);
        post.setPostId(postIdInt);
    }


    public void changePostTextAndAddPicture(Post post) {
        ApiUtils.makePostApiRequest(getApiUrl(getUploadServer));
        ApiUtils.uploadFile(ApiUtils.parseForText(uploadUrl), picture);
        String photoId = ApiUtils.parseForText(photo);
        int serverID = ApiUtils.parseForInt(server);
        String pictureHash = ApiUtils.parseForText(hash);
        ApiUtils.makePostApiRequest(getApiUrl(String.format(savesWallPhoto, photoId, serverID, pictureHash)));
        String randomText = RandomTextMaker.getRandomWord();
        post.setText(randomText);
        int pictureId = ApiUtils.parseForInt(photoIdText);
        post.setPictureId(pictureId);
        ApiUtils.makePostApiRequest(getApiUrl(String.format(wallEdit, post.getPostId(), randomText, USER_ID, pictureId)));
    }

    public void makeComment(Post post) {
        String randomText = RandomTextMaker.getRandomWord();
        post.setCommentText(randomText);
        ApiUtils.makePostApiRequest(getApiUrl(String.format(wallComment, post.getPostId(), randomText)));
    }

    public String likeNameGet(Post post) {
        ApiUtils.makePostApiRequest(getApiUrl(String.format(likesGet, post.getPostId())));
        return ApiUtils.parseForArray(userLikeId).toString();
    }

    public void postDelete(Post post) {
        ApiUtils.makePostApiRequest(getApiUrl(String.format(wallDelete, post.getPostId())));
    }
}
