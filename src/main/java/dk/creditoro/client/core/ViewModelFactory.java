package dk.creditoro.client.core;

import dk.creditoro.client.view.add_credits.AddCreditViewModel;
import dk.creditoro.client.view.browse_channels.BrowseChannelsViewModel;
import dk.creditoro.client.view.browse_productions.BrowseProductionsViewModel;
import dk.creditoro.client.view.login.LoginViewModel;
import dk.creditoro.client.view.production.ProductionViewModel;

/**
 * The type View model factory is responsible for creating and making the View Model available.
 * Pattern: Factory method - https://en.wikipedia.org/wiki/Factory_method_pattern
 */
public class ViewModelFactory {


    private final LoginViewModel loginViewModel;
    private final BrowseChannelsViewModel browseChannelsViewModel;
    private final BrowseProductionsViewModel browseProductionsViewModel;
    private final ProductionViewModel productionViewModel;
    private final AddCreditViewModel addCreditViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        loginViewModel = new LoginViewModel(modelFactory.getUserModel());
        browseChannelsViewModel = new BrowseChannelsViewModel(modelFactory.getChannelModel(), modelFactory.getUserModel());
        browseProductionsViewModel = new BrowseProductionsViewModel(modelFactory.getProductionModel(), modelFactory.getUserModel());
        productionViewModel = new ProductionViewModel(modelFactory.getCreditModel(), modelFactory.getUserModel(), this);
        addCreditViewModel = new AddCreditViewModel(modelFactory.getCreditModel(), modelFactory.getUserModel(), this);

    }

    /**
     * Gets login view model.
     *
     * @return the login view model
     */
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
    public BrowseChannelsViewModel getBrowseChannelsViewModel() {
        return browseChannelsViewModel;
    }
    public BrowseProductionsViewModel getBrowseProductionsViewModel() {
        return browseProductionsViewModel;
    }
    public ProductionViewModel getProductionViewModel()
    {
        return productionViewModel;
    }

    public AddCreditViewModel getAddCreditViewModel() {
        return addCreditViewModel;
    }
}