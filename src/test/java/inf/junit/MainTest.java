package inf.junit;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
	@Test
    public void agregarVuelo_deberiaAgregarUnNuevoVuelo() {
        Main.agregarVuelo("Origen1", "Destino1", "12:00", 120, 100);
        assertTrue(Main.existeVuelo("Origen1", "Destino1", "12:00"));
    }

    @Test
    public void agregarVuelo_deberiaAgregarUnNuevoVuelo2() {
        Main.agregarVuelo("Origen2", "Destino2", "12:00", 120, 100);
        assertTrue(Main.existeVuelo("Origen2", "Destino2", "12:00"));
    }
    @Test
    public void testGetVuelo() {	
        Main.agregarVuelo("Origen3", "Destino3", "12:00", 120, 100);
        assertEquals("Destino3", Main.getVuelo("OriDes1200").getDestino());
    }
    @Test
    public void testBuscarVuelo() {
        Main.agregarVuelo("Origen3", "Destino3", "12:00", 120, 100);
        assertEquals("Origen3", Main.buscarVuelos("Origen3", "Destino3").get("OriDes1200").getOrigen());
    }
    @Test
    public void testBuscarVueloNull() {
        Main.agregarVuelo("Origen3", "Destino3", "12:00", 120, 100);
        // assert on map empty
        assertTrue(Main.buscarVuelos("Origen3", "Destino4").isEmpty());
    }
    @Test
    public void testEliminarVuelo() {
        Main.agregarVuelo("Origen4", "Destino4", "12:00", 120, 100);
        Main.eliminarVuelo("OriDes1200");
        assertFalse(Main.existeVuelo("Origen4", "Destino4", "12:00"));
    }
    @Test
    public void testModificarVuelo() {
        Main.agregarVuelo("Origen5", "Destino5", "12:00", 120, 100);
        Main.modificarVuelo("OriDes1200", 450);
        assertEquals(450, Main.getVuelo("OriDes1200").getAsientosDisponibles());
    }
    @Test
    public void testValidarCodigoVuelo() {
        Main.agregarVuelo("Origen5", "Destino5", "12:00", 120, 100);
        assertTrue(Main.validarCodigoVuelo("OriDes1200"));
    }
}
