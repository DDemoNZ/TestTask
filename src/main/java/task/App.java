package task;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
import task.api.dto.UserDto;
import task.db.UserDao;
import task.resource.AuthenticatedResources;
import task.resource.NameResource;
import task.resource.SmtpResources;
import task.security.basic.BasicAuthenticator;
import task.smtp.SmtpService;

public class App extends Application<Config> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
    }

    @Override
    public void run(Config config, Environment environment) {
        registerResources(config, environment);
        registerAuth(environment);
    }

    private void registerAuth(Environment environment) {
        environment.jersey().register(new SmtpService());
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter
                .Builder<UserDto>()
                .setAuthenticator(new BasicAuthenticator())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserDto.class));
    }

    private void registerResources(Config config, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDatabase(), "postgresql");
        final UserDao userDao = jdbi.onDemand(UserDao.class);

        environment.jersey().register(new NameResource(userDao));
        environment.jersey().register(new AuthenticatedResources());
        environment.jersey().register(new SmtpResources(new SmtpService()));
    }
}
