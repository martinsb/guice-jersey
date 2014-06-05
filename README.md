A Guice module for easier Jersey's ApplicationHandler configuration
====================

guice-jersey provides `JerseyModule` Guice module that gives an easy mechanism for injecting Guice dependencies into JAX-RS resource handlers managed by Jersey.
This module provides access to Jersey's low-level entry point, `ApplicationHandler`. Could be useful when dealing with non-servlet environments, such as Netty NIO client server framework.

Example usage
---------------------

* Add `guice-jersey` dependency to your Maven project:
``````
<dependency>
<groupId>me.barinskis.inject</groupId>
    <artifactId>guice-jersey</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
``````

* If not already done, configure your JAX-RS resources allow dependency @Inject'ion
`````
@Path("/sample")
public class SampleResource {

    private final FooService service;
    
    @Inject
    public SampleResource(FooService service) {
        this.service = service;
    }

    @GET
    public String test() {
        return service.bar();
    }
}
`````

* Extend provided `JerseyModule` to configure your resources:
`````
public class SampleJerseyModule extends JerseyModule {
    
    @Override
    protected void configureResources() {
        //scan method recursively finds all the resource classes (annotated with @Path) in the given java.lang.Package
        //and injects the required dependencies 
        scan(SampleResource.class.getPackage());
        //install another dependent module?
        install(new FooModule());
    }
}
`````

* Create Guice injector to retrieve Jersey's `ApplicationHandler` instance and use it:
``````
Injector injector = Guice.createInjector(new SampleJerseyModule());
ApplicationHandler handler = injector.getInstance(ApplicationHandler.class);
handler.handle(...);
``````