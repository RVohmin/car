package persistence;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class HbmCarLogicTest {
    HbmCarLogic hbmCarLogic = HbmCarLogic.instOf();

    //Section Engine tests
    @Test
    public void whenCreateEngine() {
        Engine expected = new Engine("V8123");
        Integer id = hbmCarLogic.createEngine(expected);
        expected.setId(id);
        assertNotNull(id);
        assertEquals(expected, hbmCarLogic.findEngineById(id));
    }

    @Test
    public void whenUpdateEngine() {
        Engine engine = new Engine("V6");
        Integer idEngine = hbmCarLogic.createEngine(engine);
        Engine expected = new Engine("V8");
        expected.setId(idEngine);
        hbmCarLogic.update(idEngine, expected);
        Engine result = hbmCarLogic.findEngineById(idEngine);
        assertEquals(expected, result);
    }

    @Test
    public void whenDeleteEngine() {
        Engine engine = new Engine("fantom");
        Integer newId = hbmCarLogic.createEngine(engine);
        engine.setId(newId);
        hbmCarLogic.deleteEngine(engine);
        assertNull(hbmCarLogic.findEngineById(newId));
    }

    @Test
    public void whenGetListOfEngines() {
        int beforeAddEngineListSize = hbmCarLogic.findAllEngines().size();
        Engine engine = new Engine("newEngine");
        hbmCarLogic.createEngine(engine);
        int afterAddEngineListSize = hbmCarLogic.findAllEngines().size();
        assertEquals(beforeAddEngineListSize, afterAddEngineListSize - 1);
    }

    //Section Car tests
    @Test
    public void whenCreateCar() {
        Engine engine = new Engine("V8123");
        Integer engineId = hbmCarLogic.createEngine(engine);
        engine.setId(engineId);
        Car car = new Car("Nissan", engine);
        Integer carId = hbmCarLogic.createCar(car);
        car.setId(carId);
        car.setEngine(engine);
        assertEquals(car.getId(), hbmCarLogic.findCarById(carId).getId());
    }

    @Test
    public void whenUpdateCar() {
        Engine engine = new Engine("V9999");
        Integer engineId = hbmCarLogic.createEngine(engine);
        engine.setId(engineId);
        Car car = new Car("Nissan", engine);
        Integer carId = hbmCarLogic.createCar(car);
        car.setId(carId);
        Car updateCar = new Car("Nissan Juke", engine);
        updateCar.setId(carId);
        hbmCarLogic.update(carId, updateCar);
        assertEquals(updateCar.getName(), hbmCarLogic.findCarById(carId).getName());
    }

    @Test
    public void whenDeleteCar() {
        Engine engine = new Engine("V9999");
        Integer engineId = hbmCarLogic.createEngine(engine);
        engine.setId(engineId);
        Car car = new Car("Nissan", engine);
        Integer carId = hbmCarLogic.createCar(car);
        car.setId(carId);
        hbmCarLogic.deleteCar(car);
        assertNull(hbmCarLogic.findCarById(carId));
    }

    @Test
    public void whenGetListOfCars() {
        int beforeAddCarsListSize = hbmCarLogic.findAllCars().size();
        Engine engine = new Engine("V9999");
        Integer engineId = hbmCarLogic.createEngine(engine);
        engine.setId(engineId);
        Car car = new Car("Nissan", engine);
        Integer carId = hbmCarLogic.createCar(car);
        car.setId(carId);
        int afterAddCarListSize = hbmCarLogic.findAllCars().size();
        assertEquals(beforeAddCarsListSize, afterAddCarListSize - 1);
    }

    // Section Drivers tests
    @Test
    public void whenCreateDriver() {
        Driver driver = new Driver("Alex");
        Integer driverId = hbmCarLogic.createDriver(driver);
        driver.setId(driverId);
        assertEquals(driver, hbmCarLogic.findDriverById(driverId));
    }

    @Test
    public void whenUpdateDriver() {
        Driver driver = new Driver("Alex");
        Integer driverId = hbmCarLogic.createDriver(driver);
        driver.setId(driverId);

        Driver newDriver = new Driver("Ivan");
        newDriver.setId(driverId);
        hbmCarLogic.update(driverId, newDriver);
        assertEquals("Ivan", hbmCarLogic.findDriverById(driverId).getName());
    }

    @Test
    public void whenDeleteDriver() {
        Driver driver = new Driver("Alex");
        Integer driverId = hbmCarLogic.createDriver(driver);
        driver.setId(driverId);
        hbmCarLogic.deleteDriver(driver);
        assertNull(hbmCarLogic.findDriverById(driverId));
    }

    @Test
    public void whenGetListOfDrivers() {
        int beforeAddDriversListSize = hbmCarLogic.findAllDrivers().size();
        Driver driver = new Driver("Alex");
        hbmCarLogic.createDriver(driver);
        int afterAddCarListSize = hbmCarLogic.findAllDrivers().size();
        hbmCarLogic.deleteDriver(driver);
        assertEquals(beforeAddDriversListSize, afterAddCarListSize - 1);
    }

    //History

    @Test
    public void whenAddHistory() {
        Engine engine = new Engine("V6");
        Integer idEngine = hbmCarLogic.createEngine(engine);
        Car car = new Car("Toyota", engine);
        Integer idCar = hbmCarLogic.createCar(car);
        Driver driver = new Driver("Mike");
        driver.addCar(car);
        car.addDriver(driver);
        Driver driver2 = new Driver("Ivan");
        driver2.addCar(car);
        car.addDriver(driver2);
        Integer idDriver1 = hbmCarLogic.createDriver(driver);
        Integer idDriver2 = hbmCarLogic.createDriver(driver2);
        assertEquals(driver.getCars().contains(car), driver2.getCars().contains(car));
        assertEquals(car.getDrivers().contains(driver), car.getDrivers().contains(driver2));
    }
}