package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import javax.management.Query;
import java.util.List;
import java.util.function.Function;

public class HbmCarLogic {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmCarLogic() {
    }

    private static final class Lazy {
        private static final HbmCarLogic INST = new HbmCarLogic();
    }

    public static HbmCarLogic instOf() {
        return Lazy.INST;
    }

    private <T> T execute(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // Engine Section methods

    public Integer createEngine(Engine engine) {
        return (Integer) execute(session -> session.save(engine));
    }

    public List<Engine> findAllEngines() {
        return execute(
                session -> session.createQuery("from Engine").list()
        );
    }

    void update(int id, Engine engine) {
        execute(session -> {
            session.update(String.valueOf(id), engine);
            return null;
        });
    }

    public Engine findEngineById(int id) {
        return execute(
                session -> session.get(Engine.class, id)
        );
    }

    public void deleteEngine(Engine engine) {
        execute(
                session -> {
                    session.delete(engine);
                    return null;
                });
    }

    // Car Section methods
    public Integer createCar(Car car) {
        return (Integer) execute(session -> session.save(car));
    }

    public List<Car> findAllCars() {
        return execute(
                session -> session.createQuery("from Car").list()
        );
    }

    void update(int id, Car car) {
        execute(session -> {
            session.update(String.valueOf(id), car);
            return null;
        });
    }

    public Car findCarById(int id) {
        return execute(
                session -> session.get(Car.class, id)
        );
    }

    public void deleteCar(Car car) {
        execute(
                session -> {
                    session.delete(car);
                    return null;
                });
    }

    // Drivers Section methods
    public Integer createDriver(Driver driver) {
        return (Integer) execute(session -> session.save(driver));
    }

    public List<Driver> findAllDrivers() {
        return execute(
                session -> session.createQuery("from Driver").list()
        );
    }

    void update(int id, Driver driver) {
        execute(session -> {
            session.update(String.valueOf(id), driver);
            return null;
        });
    }

    public Driver findDriverById(int id) {
        return execute(
                session -> session.get(Driver.class, id)
        );
    }

    public void deleteDriver(Driver driver) {
        execute(
                session -> {
                    session.delete(driver);
                    return null;
                });
    }
}
