package dk.creditoro.client.core;

import dk.creditoro.client.view.login.LoginViewModel;

/**
 * The type View model factory is responsible for creating and making the View Model available.
 * Pattern: Factory method - https://en.wikipedia.org/wiki/Factory_method_pattern
 */
public class ViewModelFactory {


    private final LoginViewModel loginViewModel;


    public ViewModelFactory(ModelFactory modelFactory) {
        loginViewModel = new LoginViewModel(modelFactory.getUserModel());
    }

    /**
     * Gets login view model.
     *
     * @return the login view model
     */
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}