package com.mycompany.designpatterns.FactoryExample;

/**
 * Pc is a subclass of computer
 */
public class Pc extends Computer{
    private String ram;
    private String hdd;
    private String cpu;

    private static final String type = "PC";

    public Pc(String ram, String hdd, String cpu){
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public String getType(){
        return type;
    }

    @Override
    public String getRam() {
        return this.ram;
    }

    @Override
    public String getHdd() {
        return this.hdd;
    }

    @Override
    public String getCpu() {
        return this.cpu;
    }
}
