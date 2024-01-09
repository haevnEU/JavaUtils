package de.haevn.utils.io;

import de.haevn.utils.exceptions.NotYetImplementedException;

import java.util.List;

public class USB {
    private static final USB INSTANCE = new USB();

    public static synchronized USB getInstance() {
        return INSTANCE;
    }

    private List<DeviceDescriptor> findDevices(){
        return List.of();
    }

    public List<Device> list(){
        throw new NotYetImplementedException();
    }

    public List<Device> list(final short vendorId){
        throw new NotYetImplementedException();
    }

    public List<Device> list(final short vendorId, final short productId){
        throw new NotYetImplementedException();
    }

    private USB() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }


    private void shutdown(){

    }

    class Device{}
    class DeviceDescriptor{}
}
