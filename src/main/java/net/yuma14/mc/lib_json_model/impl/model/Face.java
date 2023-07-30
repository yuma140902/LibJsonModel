package net.yuma14.mc.lib_json_model.impl.model;

public class Face {
    public final Vertex p0;
    public final Vertex p1;
    public final Vertex p2;
    public final Vertex p3;
    public final TextureId texture;

    protected Face(Vertex p0, Vertex p1, Vertex p2, Vertex p3, TextureId texture) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.texture = texture;
    }
}
