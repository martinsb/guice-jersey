package me.barinskis.inject.guice.jersey;

import java.util.Set;

interface ResourceProvider {
    Set<Class<?>> getResourceClasses();
}
