package constants;

import helpers.PropertiesHelpers;
import helpers.SystemHelpers;

import java.io.File;

public class Constants {
    public static final String LINK_WEBSITE = "https://demo5.cybersoft.edu.vn/";
    public static final String LINK_LOGIN = LINK_WEBSITE + "login";
    public static final String LINK_TRENDINGSERVICE_PAGE = LINK_WEBSITE + "title/1";
    public static final String LINK_DESCRIPTIONSERVICE_PAGE = LINK_WEBSITE + "jobDetail/25";
    public static final String EXPECTED_TAG_URL_CATEGORIES_2 = LINK_WEBSITE + "categories/2";
    public static final String BROWSER_NOT_SUPPORT = "This browser is not support";
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";

    /*
        @Epic ==========================================================================================================
     */
    public static final String EPIC_SEARCH_AND_HIRESERVICE = "Tìm kiếm và thuê dịch vụ trên Website Fiver Cybersoft";

     /*
        @STORY =========================================================================================================
     */
    public static final String USERSTORY_201 = "Trending Service Page (View service details)";
    public static final String USERSTORY_202 = "Service Descriptions (View service details)";
    public static final String USERSTORY_203 = "Service Pricing Table (View service details)";
    public static final String USERSTORY_204 = "Seller Info (View service details)";
    public static final String USERSTORY_205 = "FAQ(Frequently Asked Questions)(View service details)";
    public static final String USERSTORY_206 = "Reviews (View service details)";
    public static final String USERSTORY_207 = "Suggest additional features (View service details)";
    public static final String USERSTORY_501 = "UI Header & Footer";

    /*
        @Feature =======================================================================================================
     */
    public static final String FEATURE_VERIFY_TRENDINGSERVICEPAGE = "Kiểm tra hiển thị / chức năng trang Trending Services";
    public static final String FEATURE_VERIFY_CATEGORIESSERVICEPAGE = "Kiểm tra hiển thị / chức năng trang Categorie Services";
    public static final String FEATURE_VERIFY_DESCRIPTIONSERVICEPAGE = "Kiểm tra hiển thị / chức năng trang Description Services";
    public static final String FEATURE_VERIFY_UI_HEADER = "Kiểm tra hiển thị / chức năng Header";
    public static final String FEATURE_VERIFY_UI_FOOTER = "Kiểm tra hiển thị / chức năng Footer";
    /*
        @Loggers =======================================================================================================
    */
    public static final String SETTING_UP_WEB_DRIVER = "./Setting up web driver";
    public static final String ACCESSED_SUCCESSFULLY = "./Accessed successfully";


    /*
        @Parameters ====================================================================================================
    */
    //public static final String BROWSER = "browser";


    /*
        @Result ========================================================================================================
    */
    public static final String TEST_PASSED = "✅ Test Passed: ";
    public static final String TEST_FAILED = "❌ Test Failed: ";


    /*
        @Report ========================================================================================================
    */

    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH");
    public static final String JSON_DATA_FILE_PATH = PropertiesHelpers.getValue("JSON_DATA_FILE_PATH");
    public static final String EXCEL_CMS_LOGIN = PropertiesHelpers.getValue("EXCEL_CMS_LOGIN");
    public static final String EXCEL_CMS_DATA = PropertiesHelpers.getValue("EXCEL_CMS_DATA");
    public static final String EXCEL_CMS_PRODUCTS_USER = PropertiesHelpers.getValue("EXCEL_CMS_PRODUCTS_USER");

    public static final String BROWSER = PropertiesHelpers.getValue("BROWSER");
    public static final String URL_CRM = PropertiesHelpers.getValue("URL_CRM");
    public static final String URL_CMS_ADMIN = PropertiesHelpers.getValue("URL_CMS_ADMIN");
    public static final String URL_CMS_USER = PropertiesHelpers.getValue("URL_CMS_USER");
    public static final String REMOTE_URL = PropertiesHelpers.getValue("REMOTE_URL");
    public static final String REMOTE_PORT = PropertiesHelpers.getValue("REMOTE_PORT");
    public static final String PROJECT_NAME = PropertiesHelpers.getValue("PROJECT_NAME");
    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");
    public static final String EXTENT_REPORT_NAME = PropertiesHelpers.getValue("EXTENT_REPORT_NAME");
    public static final String EXTENT_REPORT_FOLDER = PropertiesHelpers.getValue("EXTENT_REPORT_FOLDER");
    public static final String EXPORT_VIDEO_PATH = PropertiesHelpers.getValue("EXPORT_VIDEO_PATH");
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelpers.getValue("EXPORT_CAPTURE_PATH");
    public static final String SEND_REPORT_TO_TELEGRAM = PropertiesHelpers.getValue("SEND_REPORT_TO_TELEGRAM");
    public static final String TELEGRAM_TOKEN = PropertiesHelpers.getValue("TELEGRAM_TOKEN");
    public static final String TELEGRAM_CHATID = PropertiesHelpers.getValue("TELEGRAM_CHATID");
    public static final String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public static final String TARGET = PropertiesHelpers.getValue("TARGET");
    public static final String HEADLESS = PropertiesHelpers.getValue("HEADLESS");
    public static final String OVERRIDE_REPORTS = PropertiesHelpers.getValue("OVERRIDE_REPORTS");
    public static final String OPEN_REPORTS_AFTER_EXECUTION = PropertiesHelpers.getValue("OPEN_REPORTS_AFTER_EXECUTION");
    public static final String SEND_EMAIL_TO_USERS = PropertiesHelpers.getValue("SEND_EMAIL_TO_USERS");
    public static final String SCREENSHOT_PASSED_TCS = PropertiesHelpers.getValue("SCREENSHOT_PASSED_TCS");
    public static final String SCREENSHOT_FAILED_TCS = PropertiesHelpers.getValue("SCREENSHOT_FAILED_TCS");
    public static final String SCREENSHOT_SKIPPED_TCS = PropertiesHelpers.getValue("SCREENSHOT_SKIPPED_TCS");
    public static final String SCREENSHOT_ALL_STEPS = PropertiesHelpers.getValue("SCREENSHOT_ALL_STEPS");
    public static final String ZIP_FOLDER = PropertiesHelpers.getValue("ZIP_FOLDER");
    public static final String ZIP_FOLDER_PATH = PropertiesHelpers.getValue("ZIP_FOLDER_PATH");
    public static final String ZIP_FOLDER_NAME = PropertiesHelpers.getValue("ZIP_FOLDER_NAME");
    public static final String VIDEO_RECORD = PropertiesHelpers.getValue("VIDEO_RECORD");

    public static final String LOCATE = PropertiesHelpers.getValue("LOCATE");
    public static final String RETRY_TEST_FAIL = PropertiesHelpers.getValue("RETRY_TEST_FAIL");

    public static final int WAIT_DEFAULT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_DEFAULT"));
    public static final int WAIT_IMPLICIT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_IMPLICIT"));
    public static final int WAIT_EXPLICIT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_EXPLICIT"));
    public static final int WAIT_PAGE_LOADED = Integer.parseInt(PropertiesHelpers.getValue("WAIT_PAGE_LOADED"));
    public static final int WAIT_SLEEP_STEP = Integer.parseInt(PropertiesHelpers.getValue("WAIT_SLEEP_STEP"));
    public static final String ACTIVE_PAGE_LOADED = PropertiesHelpers.getValue("ACTIVE_PAGE_LOADED");

    public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
    public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";
    public static String EXTENT_REPORT_FILE_PATH = EXTENT_REPORT_FOLDER_PATH + File.separator + EXTENT_REPORT_FILE_NAME;
    public static final String ZIPPED_EXTENT_REPORTS_FOLDER = EXTENT_REPORT_FOLDER + ".zip";
    public static final String YES = "yes";
    public static final String NO = "no";

    /* ICONS - START */

    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";

    public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
    public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
    public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";

    public static final String ICON_BROWSER_OPERA = "<i class=\"fa fa-opera\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_SAFARI = "<i class=\"fa fa-safari\" aria-hidden=\"true\"></i>";

    public static final String ICON_Navigate_Right = "<i class='fa fa-arrow-circle-right' ></i>";
    public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";
    /* style="text-align:center;" */

    public static final String ICON_SOCIAL_GITHUB_PAGE_URL = "https://anhtester.com/";
    public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/anhtester/";
    public static final String ICON_SOCIAL_GITHUB_URL = "https://github.com/anhtester";
    public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
            + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
            + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";
    public static final String ICON_CAMERA = "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";

    public static final String ICON_BROWSER_PREFIX = "<i class=\"fa fa-";
    public static final String ICON_BROWSER_SUFFIX = "\" aria-hidden=\"true\"></i>";
    /* ICONS - END */

}
