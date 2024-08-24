package vn.edu.likelion.farm_management.common.constants;

/**
 * ApiPath -
 *
 * @param
 * @return
 * @throws
 */
public interface ApiPath {

    String DOMAIN = "https://drafarm.up.railway.app";
    String DOMAIN_LOCAL_FE = "http://localhost:5173/";

    //http methods
    String ID = "/{id}";

    String DATE = "/{date}";


    String SLUG = "/slug/{slug}";
    String DETAIL = "/detail";
    String ADD = "/add";
    String EDIT = "/update";
    String DELETE = "/delete";
    String DELETE_ALL = "/deleteAll";
    String CANCEL = "/cancel";
    String GET_PAGE = "/page";
    String EXPORT = "/export";
    String VERIFY_TOKEN = "/{verify-token}";
    String REGISTER = "/register";
    String DOWNLOAD = "/download";
    String FIND_ALL = "/findAll";
    String FIND_BY_ID = "/findById";
    String SEARCH = "/search";

    // Authenticate APIs
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String SIGN_UP = "/sign-up";
    String RESET_PASSWORD = "/reset-password";
    String AuthInFo = "/auth-info";
    String GOOGLE_LOGIN = "/google";
    String CHECK_PHONE_NUMBER_SIGNUP = "/check-phone-signup";
    String VERIFY_EMAIL = "/verify-email";
    String EMAIL_SIGNUP = "/email-signup";
    String SIGNUP_VERIFY = "/signup-verify";
    String FORGOT_PASSWORD = "/forgot-password";
    String REFRESH_TOKEN = "/refresh-token";

    String BASE_API_URL = "/api/v1";

    // User
    String USER_API = BASE_API_URL + "/user";
    String USER_API_findById =   ID;
    String USER_API_updateInfo =   "/updateInfo/{id}";
    String USER_API_updateAvatar =  "/updateAvatar/{id}";
    String USER_API_updateBanner =  "/updateBanner/{id}";

    // Plant
    String PLANT_API = BASE_API_URL + "/plant";
    String PAGINATE = "/paginate";
    String TYPE_PLANT ="/findAllType";

    String FIND_ALL_PLANT_BY_FARM = "/findPlantByFarm";

    // Farm
    String FARM_API = BASE_API_URL + "/farm";
    String ADD_TO_FARM = "/addToFarm";
    String TOTAL_PLANTED_AREA_ALL_FARM = "/totalPlantedAreaAllFarm";

    // Harvest
    String HARVEST_API = BASE_API_URL + "/harvest";

    String ADD_ALL = "/addAll";

    // Report
    String REPORT_API = BASE_API_URL + "/report";
}
