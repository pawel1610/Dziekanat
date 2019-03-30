package pl.pwn.reaktor.dziekanat.utils;

// klasa do nawiazywania i utrzymywania polaczenia z baza danych - implementacja singletona

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.service.ServiceRegistry;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.model.User;

public class HibernateUtils {
    private static final SessionFactory SESSION_FACTORY;

    private HibernateUtils(){
        throw new IllegalStateException("HibernateUtils is only utility class");
    }

    static {  // blok statyczny jest wywolywany przed konstruktorem
        try {
            Configuration configuration = new Configuration(); // nawiazanie polacznia
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            //dzieki addAnotatedClass mamy automatyczne mapowanie danej klasy na tab. w bazie
            //musimy dla kazdej Entity(encji) dodawac nowy wpis zeby dzialalo mapowanie
            MetadataSources sources = new MetadataSources(serviceRegistry);
            sources.addAnnotatedClass(User.class);
            sources.addAnnotatedClass(Student.class);

            Metadata metadata = sources.getMetadataBuilder().build();

            SESSION_FACTORY = metadata.getSessionFactoryBuilder().build();


        } catch (Exception e){
            System.out.println("SESSION_FACTORY coud not be created\n" + e);
            throw new ExceptionInInitializerError(e); // rzuci errorem i zamknie program
        }
    }
    public static void initSessionFactory(){
        System.out.println("Initialize Hibernate session factory");
    }
    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }

    public static void destroySessionFactory(){
        System.out.println("Session factory is destroyed");
        SESSION_FACTORY.close();
    }
}
