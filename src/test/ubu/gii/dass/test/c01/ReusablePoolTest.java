/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;
/**
 * @author alumno
 *
 */
public class ReusablePoolTest {

	private ReusablePool pool;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pool = ReusablePool.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		pool = null;
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool pool = ReusablePool.getInstance();
		// no es nulo 
		assertNotNull(pool);
		// Es el obj devuelto es una instancia de ReusablePool
		assertTrue(pool instanceof ReusablePool);
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * En este test se comprueba que el método acquireReusable() devuelva y elimine
	 * el elemento de la lista reusable de la clase ReusablePool.
	 * Además, se comprueba que se lance la excepción correctamente si se intenta
	 * eliminar elementos si la lista está vacía 
	 */
	@Test
	public void testAcquireReusable() {
		ReusablePool pool = ReusablePool.getInstance();
		//Creamos una variable de tipo NotFreeInstanceException
		NotFreeInstanceException excepcion = null;
		assertNotNull(pool);
		try {
			//Obtenemos dos objetos de la clase Reusable con el método acquireReusable()
			Reusable r = pool.acquireReusable();
			Reusable r2 = pool.acquireReusable();
			//Probamos que el método acquireReusable no devuelva el mismo objeto reusable
			assertNotEquals(r, r2);
			//Llamamos de nuevo al método acquireReusable para comprobar que nos salta la excepción NotFreeInstanceException
			Reusable r3 = pool.acquireReusable();
		} catch (NotFreeInstanceException e) {
			//Almacenamos la excepción obtenida
			excepcion = e;
		}
		//Determinamos que la excepción sea la esperada
		assertEquals(excepcion.getMessage(), "No hay más instancias reutilizables disponibles. Reintentalo más tarde");
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * @throws NotFreeInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException {

	}

}
