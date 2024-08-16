package vn.edu.likelion.farm_management.common.constants;

/**
 * ApiPath -
 *
 * @param
 * @return
 * @throws
 */
public interface ApiPath {

    String BASE_API_URL = "/api/v1";
    String USER_API = BASE_API_URL + "/user";
    String PLANT_API = BASE_API_URL + "/plant";
    String FARM_API = BASE_API_URL + "/farm";
    String HARVEST_API = BASE_API_URL + "/harvest";
    String REPORT_API = BASE_API_URL + "/report";


    //http methods

    String ID = "/{id}";
    String SLUG = "/slug/{slug}";
    String DETAIL = "/detail";
    String ADD = "/add";
    String EDIT = "/edit";
    String DELETE = "/delete";
    String CANCEL = "/cancel";
    String GET_PAGE = "/page";
    String EXPORT = "/export";
    String VERIFY_TOKEN = "/{verify-token}";
    String REGISTER = "/register";
    String DOWNLOAD = "/download";
    String GET_ALL = "/getAll";

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

}
