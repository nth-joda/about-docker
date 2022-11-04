package com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2;

public class WoodenFunitureFactory extends FunitureFactoryAbstract {
    private Chair chair = new WoodenChair();

    private Table table = new WoodenTable();

    public Chair createChair(){
        this.chair.create();
        return this.chair;
    }

    public Table createTable(){
        this.table.create();
        return this.table;
    }
}
