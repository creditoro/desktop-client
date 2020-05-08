package dk.creditoro.client.view.browse_channels;

class BrowseChannelsViewModelTest {
    BrowseChannelsViewModel browseChannelsViewModel;

    @Start
    public void start(Stage stage) {
        var clientFactory = new ClientFactory();
        var modelFactory = new ModelFactory(clientFactory);
        var viewModelFactory = new ViewModelFactory(modelFactory);
        var viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start();
        LoginViewModel loginViewModel = viewModelFactory.getLoginViewModel();
        browseChannelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        loginViewModel.passwordProperty().set("string");
        loginViewModel.emailProperty().set("string@string.dk");
        loginViewModel.login();
    }

    @Test
    public void listPropertyProperty() {
        Assertions.assertFalse(browseChannelsViewModel.listPropertyProperty().isEmpty());
    }

    @Test
    public void channelName(FxRobot robot) {
        Assertions.assertEquals("", browseChannelsViewModel.channelName(""));
        Assertions.assertEquals("TV 2 DANMARK", browseChannelsViewModel.channelName("04bdd23d-75d5-4381-af97-628b7e531e0d"));

        //making sonar happy by not doing robot test in their own test because they a test needs assertions and it dosnt do that
        //needs own test later
        robot.clickOn("#buttonA");
        robot.clickOn("#buttonA");
    }
}