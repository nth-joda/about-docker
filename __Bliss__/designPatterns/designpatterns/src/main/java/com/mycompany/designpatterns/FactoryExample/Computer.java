package com.mycompany.designpatterns.FactoryExample;

public abstract class Computer {
    public abstract String getRam();
    public abstract String getHdd();
    public abstract String getCpu();

    public abstract String getType();

    @Override
    public String toString(){
        return this.getType()+": RAM= "+this.getRam() +"; HDD= "+this.getHdd()+"; CPU= "+this.getCpu();

    }
}
