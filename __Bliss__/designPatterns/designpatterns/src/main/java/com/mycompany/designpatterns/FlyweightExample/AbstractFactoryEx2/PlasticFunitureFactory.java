package com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2;

public class PlasticFunitureFactory extends FunitureFactoryAbstract {
    private Chair chair = new PlasticChair();
    private Table table = new PlasticTable();
    public Chair createChair(){
        this.chair.create();
        return this.chair;
    }

    public Table createTable(){
        this.table.create();
        return this.table;
    }
}
