package vkapi.tests;

import framework.logger.Log;
import framework.utils.ReadValuesFromConfig;
import org.testng.annotations.Test;
import vkapi.pageobject.enums.SideMenuLabels;
import vkapi.pageobject.models.Post;
import vkapi.pageobject.forms.MainPage;
import vkapi.pageobject.forms.MyVkPage;
import vkapi.pageobject.forms.NewsPage;
import vkapi.pageobject.forms.PostForm;
import vkapi.pageobject.utills.RandomTextMaker;
import vkapi.pageobject.utills.VkApiUtils;

import static org.testng.Assert.*;

public class VkTest extends BaseTest {
    private String email = ReadValuesFromConfig.readValues("email", ReadValuesFromConfig.testPropertiesFile);
    private String password = ReadValuesFromConfig.readValues("password", ReadValuesFromConfig.testPropertiesFile);

    @Test
    private void vkTest() {

        VkApiUtils vkApi = new VkApiUtils();
        MainPage main = new MainPage();

        Log.info("Checking if main page is opened");
        assertTrue(main.pageHasUniqueElement());

        Log.step("Logging in");
        main.logIn(email, password);
        NewsPage news = new NewsPage();

        Log.info("Checking if new page is opened");
        assertTrue(news.formIsVisible());

        Log.step("Opening my vk page");
        news.getSideMenuForm().sideMenuItemClick(SideMenuLabels.MY_PROFILE);
        MyVkPage myVkPage = new MyVkPage();

        Log.info("Checking if my vk page is opened");
        assertTrue(myVkPage.pageHasUniqueElement());

        Log.step("Creating post on my page");
        Post post = new Post(RandomTextMaker.getRandomWord());
        vkApi.createPost(post);

        Log.info("Checking if post is created");
        PostForm postForm = new PostForm(post);
        assertTrue(postForm.postIsVisible() && postForm.rightUserTextAdd(post));

        Log.step("Changing text and picture of recently created post");
        vkApi.changePostTextAndAddPicture(post);

        Log.info("Checking if text changed and picture added");
        assertTrue(postForm.rightUserAddedImage(post) && postForm.rightUserTextAdd(post));

        Log.step("Making comment");
        vkApi.makeComment(post);

        Log.info("Checking if comment is created");
        assertTrue(postForm.commentIsVisible(post));

        Log.step("Clicking on like button of created post");
        postForm.likeClick();

        Log.info("Checking if right user licked post");
        assertTrue(vkApi.likeNameGet(post).contains(postForm.getUserId()));

        Log.step("deleting post");
        vkApi.postDelete(post);

        Log.info("Checking if post deleted");
        assertTrue(postForm.postDeleted());
    }
}
