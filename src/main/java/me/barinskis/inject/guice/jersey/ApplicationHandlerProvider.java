package me.barinskis.inject.guice.jersey;

import com.google.inject.Injector;
import com.google.inject.Provider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.server.ApplicationHandler;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.core.Application;
import java.util.Set;

class ApplicationHandlerProvider implements Provider<ApplicationHandler> {

    @Inject
    public ApplicationHandlerProvider(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public ApplicationHandler get() {
        ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator(null);
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);


        Application app = new Application() {
            @Override
            public Set<Class<?>> getClasses() {
                return resourceProvider.getResourceClasses();
            }
        };

        return new ApplicationHandler(app, null, serviceLocator);
    }

    @Inject private Injector injector;

    private final ResourceProvider resourceProvider;
}
