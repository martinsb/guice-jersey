package me.barinskis.inject.guice.jersey;

import com.google.inject.Inject;
import com.google.inject.matcher.Matchers;

import javax.ws.rs.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PackageScanningResourceProvider implements ResourceProvider {

    @Inject
    public PackageScanningResourceProvider(@Resources List<Package> packages) {
        Set<Class<?>> set = new HashSet<Class<?>>();

        for (Package pack : packages) {
            set.addAll(Classes.matching(Matchers.annotatedWith(Path.class)).in(pack));
        }

        resourceClasses = Collections.unmodifiableSet(set);
    }

    @Override
    public Set<Class<?>> getResourceClasses() {
        return resourceClasses;
    }

    private final Set<Class<?>> resourceClasses;
}
