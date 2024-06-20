package mcjty.lostcities.dimensions.world.lost.cityassets;

import com.google.gson.JsonObject;
import mcjty.lostcities.api.ILostCityAsset;
import mcjty.lostcities.dimensions.world.lost.Orientation;

public class PredefinedHighway implements ILostCityAsset {

    private String name;
    private int dimension;
    private int chunkX;
    private int chunkZ;
    private Direction direction;
    private int length;
    private int level;

    public PredefinedHighway(JsonObject object) {
        readFromJSon(object);
    }

    public PredefinedHighway(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void readFromJSon(JsonObject object) {
        name = object.get("name").getAsString();
        dimension = object.get("dimension").getAsInt();
        chunkX = object.get("chunkx").getAsInt();
        chunkZ = object.get("chunkz").getAsInt();
        switch (object.get("direction").getAsString().toLowerCase()) {
            case "x":
            case "x+":
                direction = Direction.X_POS;
                break;
            case "x-":
                direction = Direction.X_NEG;
                break;
            case "z":
            case "z+":
                direction = Direction.Z_POS;
                break;
            case "z-":
                direction = Direction.Z_NEG;
                break;
            default:
                throw new IllegalArgumentException("Invalid highway direction: " + object.get("direction"));
        }
        length = object.get("length").getAsInt();
        level = object.has("level") ? object.get("level").getAsInt() : 0;
    }

    public int getDimension() {
        return dimension;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public int getLevel() {
        return level;
    }

    public enum Direction {

        X_POS(Orientation.X, 1, 0),
        X_NEG(Orientation.X, -1, 0),
        Z_POS(Orientation.Z, 0, 1),
        Z_NEG(Orientation.Z, 0, -1);

        public final Orientation orientation;
        public final int offsetX, offsetZ;

        Direction(Orientation orientation, int offsetX, int offsetZ) {
            this.orientation = orientation;
            this.offsetX = offsetX;
            this.offsetZ = offsetZ;
        }

    }

}
