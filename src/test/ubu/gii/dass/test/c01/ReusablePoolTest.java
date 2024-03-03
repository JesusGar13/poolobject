/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;
/**
 * @author Celia Martinez Ortega, Jesus García Ballesteros
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
	 * En este test se comprueba que el metodo getInstance() devuelve siempre 
	 * el objeto unico ReusablePool que hace de singleton.
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
	 * eliminar elementos si la lista está vacía.
	 * @throws NotFreeInstanceException 
	 */
	@Test
	public void testAcquireReusable() throws NotFreeInstanceException {
		ReusablePool pool = ReusablePool.getInstance();
		assertNotNull(pool);
		
		//Obtenemos dos objetos de la clase Reusable con el método acquireReusable()
		Reusable r = pool.acquireReusable();
		Reusable r2 = pool.acquireReusable();
		//Probamos que el método acquireReusable no devuelva el mismo objeto reusable
		assertNotEquals(r.util(), r2.util());

	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * En este test se comprueba que el método releaseReusable(Reusable) añada de
	 * forma correcta a la lista reusable de la clase ReusablePool un elemento
	 * Reusable. También se comprueba que se lance la expcepción cuando se intenta
	 * añadir un elemento ya existente en dicha lista.
	 * @throws NotFreeInstanceException 
	 * @throws DuplicatedInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException, DuplicatedInstanceException {	
		ReusablePool pool = ReusablePool.getInstance();
		assertNotNull(pool);
		//Almacenamos con el método acquireReusable() para obtener un objeto de la clase Reusable
		Reusable r = pool.acquireReusable();
		
		//Llamamos al método releaseReusable(Reusable)
		pool.releaseReusable(r);
		//Comprobamos que el elemento se haya almacenado correctamente
		assertEquals(pool.acquireReusable(), r);
		
		pool.releaseReusable(r);
	}

}