package dk.creditoro.client.view;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;

public interface IViewController {
    void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler);
}
