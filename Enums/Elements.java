/*
 * Enumeration Class for the level editor each enum corresponds to a number that is used to fill in an array that contains
 * data about what objects a level contains.
 */

package Enums;

public enum Elements {
    None(0),
    Platform(1),
    Player(2),
    BasicEnemy(3),
    Flag(4);

    public final int id;

    Elements(int id) {
        this.id = id;
    }

    public static Elements getElements(int id) {
        for(Elements element : values()) {
            if(element.id == id)
                return element;
        }

        return None;
    }
}
