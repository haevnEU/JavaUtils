package de.haevn.utils.io;

import de.haevn.utils.exceptions.NotYetImplementedException;

import java.util.List;

/**
 * <h1>USB</h1>
 * <p>This class provides an interface to find and interact with USB devices.</p>
 * <b>THIS FEATURE WILL BE IMPLEMENTED WITH VERSION 1.2</b>
 *
 * @version 1.2
 * @since 1.1
 */
public class USB {
    private static final USB INSTANCE = new USB();

    public static synchronized USB getInstance() {
        return INSTANCE;
    }

    private List<DeviceDescriptor> findDevices() {
        return List.of();
    }

    public List<Device> list() {
        throw new NotYetImplementedException();
    }

    public List<Device> list(final short vendorId) {
        throw new NotYetImplementedException();
    }

    public List<Device> list(final short vendorId, final short productId) {
        throw new NotYetImplementedException();
    }

    private USB() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }


    private void shutdown() {
        // This method is called when the application is closed
    }

    public interface Device {
    }

    public interface DeviceDescriptor {
    }
}
