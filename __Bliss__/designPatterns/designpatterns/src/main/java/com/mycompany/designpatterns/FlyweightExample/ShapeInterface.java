package com.mycompany.designpatterns.FlyweightExample;
import java.awt.Color;
import java.awt.Graphics;

public interface ShapeInterface {
    void draw(Graphics g, int x, int y, int width, int height,
                     Color color);
}
