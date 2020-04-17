package dk.creditoro.client.core;

import dk.creditoro.client.view.login_page.LoginViewModel;

/**
 * The type View model factory.
 */
public class ViewModelFactory {


    /**
     * Gets login view model.
     *
     * @return the login view model
     */
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    private final LoginViewModel loginViewModel;

    /**
     * Instantiates a new View model factory.
     *
     * @param mf the mf
     */
    public ViewModelFactory(ModelFactory mf) {
        loginViewModel = new LoginViewModel(mf.getModel());
    }
}