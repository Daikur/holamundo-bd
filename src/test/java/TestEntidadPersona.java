
import beans.domain.Persona;
import static org.junit.Assert.assertTrue;
import javax.persistence.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEntidadPersona {

    static EntityManager em = null;
    static EntityTransaction tx = null;
    static EntityManagerFactory emf = null;
    Logger log = Logger.getLogger("TestEntidadPersona");

    @BeforeClass
    public static void init() throws Exception {
        emf = Persistence.createEntityManagerFactory("PersonaPU");
    }

    @Before
    public void setup() {
        try {
            em = emf.createEntityManager();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testPersonaEntity() {
        System.out.println("Iniciando test Persona Entity con JPA");
        assertTrue(em != null);
        EntityTransaction tx = em.getTransaction();
        tx.begin();

//No se debe especificar el ID, ya que se genera en automatico
        Persona persona1 = new Persona("Pepin", "Nieto", "Larios", "pepin@hotmail.es", "675345298");

        log.debug("Objeto a persistir:" + persona1);
        em.persist(persona1);

        assertTrue(persona1.getId()!=null);

        tx.commit();
        log.debug("Objeto persistido:" + persona1);
        System.out.println("Fin test Persona Entity con JPA");
    }

    @After
    public void onClose() throws Exception {
        if (em != null) {
            em.close();
        }
    }
}
