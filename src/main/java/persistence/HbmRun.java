package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            MarkCar xTrail = MarkCar.of("X-Trail");
            MarkCar juke = MarkCar.of("Juke");
            MarkCar navaro = MarkCar.of("Navaro");
            MarkCar almera = MarkCar.of("Almera");
            MarkCar quashqai = MarkCar.of("Quashqai");

            session.save(xTrail);
            session.save(juke);
            session.save(navaro);
            session.save(almera);
            session.save(quashqai);

            ModelCar nissan = ModelCar.of("Nissan");
            nissan.addMark(xTrail);
            nissan.addMark(juke);
            nissan.addMark(navaro);
            nissan.addMark(almera);
            nissan.addMark(quashqai);

            session.save(nissan);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
