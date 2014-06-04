package me.barinskis.inject.guice.jersey;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.glassfish.jersey.server.ApplicationHandler;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martins Barinskis (martins.barinskis@gmail.com)
 */
public class JerseyModule extends AbstractModule {

    @Override
    protected void configure() {
        configureResources();

        bind(new TypeLiteral<List<Package>>() {})
            .annotatedWith(Resources.class)
            .toInstance(packages);

        bind(ResourceProvider.class).to(PackageScanningResourceProvider.class);
        bind(ApplicationHandler.class).toProvider(ApplicationHandlerProvider.class).in(Singleton.class);
    }

    protected void scan(Package pack) {
        packages.add(pack);
    }

    protected void configureResources() {

    }

    private final List<Package> packages = new ArrayList<Package>();
}
